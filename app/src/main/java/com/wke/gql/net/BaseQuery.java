package com.wke.gql.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BaseQuery {


    public BaseQuery() {
    }

    public <T> T excute(Call<T> call) {
        if (call == null) return null;
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

    public <T> void enqueue(Call<T> call, final OnHttpSuccessListener<T> successListener, final OnHttpFailureListener<T> failureListener) {
        if (call == null) return;
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
        }
    }


    public interface OnHttpSuccessListener<T> {
        public void onSuccess(T response);
    }

    public interface OnHttpFailureListener<T> {
        public void onFail(Call<T> call, Throwable t);
    }
}
