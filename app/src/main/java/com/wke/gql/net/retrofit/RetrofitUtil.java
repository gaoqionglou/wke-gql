package com.wke.gql.net.retrofit;

import com.wke.gql.utils.Contants;
import com.wke.gql.utils.GsonUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/11/12.
 */

public abstract class RetrofitUtil {
    public static Retrofit retrofit;

    public static <T> T initRetrofitService(Class<T> service){
        return getRetrofitInstance().create(service);
    }

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Contants.HTTP_QUERY_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGsonInstance()))
                    .build();
        }
        return retrofit;
    }


}
