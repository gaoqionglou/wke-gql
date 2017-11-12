package com.wke.gql.net;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class BaseQuery{

    public static <T> T excute(Call<T> call){
        if(call == null) return null;
        try {
            Response<T> response = call.execute();
            if(response!=null) {
                return response.body();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static <T> void enqueue(Call<T> call,final OnHttpSuccessListener onHttpSuccessListener,final OnHttpFailureListener onHttpFailureListener){
        if(call == null) return;
        try {
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    if(!call.isCanceled()){
                        onHttpSuccessListener.onSuccess(response.body());
                    }
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    t.printStackTrace();
                    onHttpFailureListener.onFail(null);
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public interface OnHttpSuccessListener {
        public void onSuccess(Object reponse);
    }

    public interface OnHttpFailureListener {
        public void onFail(Object reponse);
    }
}
