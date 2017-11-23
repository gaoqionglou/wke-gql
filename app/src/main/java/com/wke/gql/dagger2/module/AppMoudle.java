package com.wke.gql.dagger2.module;

import com.google.gson.Gson;
import com.wke.gql.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/11/18.
 */
@Module
public class AppMoudle {
    BaseApplication baseApplication;
    Retrofit retrofit;
    Gson gson;

    public AppMoudle(BaseApplication baseApplication, Retrofit retrofit, Gson gson) {
        this.baseApplication = baseApplication;
        this.retrofit = retrofit;
        this.gson = gson;
    }

    @Singleton
    @Provides
    public BaseApplication providesBaseApplication() {
        return baseApplication;
    }

    @Singleton
    @Provides
    public Retrofit providesRetrofit() {
        return retrofit;
    }

    @Singleton
    @Provides
    public Gson providesGson() {
        return gson;
    }


}
