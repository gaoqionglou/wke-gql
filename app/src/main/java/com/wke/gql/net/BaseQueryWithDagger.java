package com.wke.gql.net;

import android.util.Log;

import com.wke.gql.dagger2.component.DaggerRetrofitComponent;
import com.wke.gql.dagger2.module.RetrofitMoudle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by gql on 17-11-15.
 */

public class BaseQueryWithDagger {
    private static final String TAG = "BaseQueryWithDagger";
    public List<Call> callList = new ArrayList<>();
    Retrofit retrofit;
    public BaseQueryWithDagger() {
        this.retrofit = DaggerRetrofitComponent.builder()
                .retrofitMoudle(new RetrofitMoudle())
                .build()
                .retrofitInstance();
    }

    public <T> T initRetrofitService(Class<T> service) {
        Log.i(TAG, "initRetrofitService: " + retrofit.toString());
        return retrofit.create(service);
    }


    public <T> T excute(Call<T> call) {
        if (call == null) return null;
        callList.add(call);
        try {
            Response<T> response = call.execute();
            if (response != null) {
                return response.body();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> void enqueue(Call<T> call, final BaseQueryWithDagger.OnHttpSuccessListener<T> successListener, final BaseQueryWithDagger.OnHttpFailureListener<T> failureListener) {
        if (call == null) return;
        callList.add(call);
        try {
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    if (!call.isCanceled()) {
                        if (successListener != null) {
                            successListener.onSuccess(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    t.printStackTrace();
                    if (failureListener != null) {
                        failureListener.onFail(call, t);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            //取消该call
            call.cancel();
        }
    }


    public void cancel() {
        for (Call call : callList) {
            if (!call.isCanceled()) {
                Log.i(TAG, "cancel retrofit call: " + call.toString());
                call.cancel();
            }
        }
    }

    public interface OnHttpSuccessListener<T> {
        public void onSuccess(T response);
    }

    public interface OnHttpFailureListener<T> {
        public void onFail(Call<T> call, Throwable t);
    }
}
