package com.wke.gql.dagger2.module;

import android.util.Log;

import com.google.gson.Gson;
import com.wke.gql.utils.Contants;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class RxNetWorkModule {
    private static final String TAG = "RxNetWorkModule";
    Gson gson;

    public RxNetWorkModule(Gson gson) {
        this.gson = gson;
    }

    @Provides
    public Retrofit providesRetrofit2() {
        if (gson == null) Log.i(TAG, "providesRetrofit2: " + "gson null");
        return new Retrofit.Builder()
                .baseUrl(Contants.HTTP_QUERY_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
