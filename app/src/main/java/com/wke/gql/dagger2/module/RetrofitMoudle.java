package com.wke.gql.dagger2.module;

import android.util.Log;

import com.google.gson.Gson;
import com.wke.gql.dagger2.component.DaggerGsonComponent;
import com.wke.gql.utils.Contants;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gql on 17-11-15.
 */
@Module
public class RetrofitMoudle {
    private static final String TAG = "RetrofitMoudle";
    @Inject
    Gson gson;

    public RetrofitMoudle() {
        this.gson = DaggerGsonComponent.builder().build().gsonInstance();
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit() {
        Log.i(TAG, "providesRetrofit: ");
        return new Retrofit.Builder()
                .baseUrl(Contants.HTTP_QUERY_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}
