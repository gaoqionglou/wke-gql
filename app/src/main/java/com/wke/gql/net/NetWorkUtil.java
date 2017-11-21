package com.wke.gql.net;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.Gson;
import com.wke.gql.dagger2.component.DaggerGsonComponent2;
import com.wke.gql.dagger2.component.DaggerNetWorkComponent;
import com.wke.gql.dagger2.module.NetWorkModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/11/18.
 */

public class NetWorkUtil {
    private static final String TAG = "NetWorkUtil";
    public List<Call> callList = new ArrayList<>();
    @Inject
    Retrofit retrofit;
    @Inject
    Gson gson;

    @Inject
    public NetWorkUtil() {

        gson = DaggerGsonComponent2.builder().build().gson();
        retrofit = DaggerNetWorkComponent.builder().netWorkModule(new NetWorkModule(gson)).build().retrofit();
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
                public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {
                    if (!call.isCanceled()) {
                        if (successListener != null) {
                            successListener.onSuccess(response.body());
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
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
