package com.wke.gql.dragger2.module;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gql on 17-11-15.
 */
@Module
public class GsonModule {
    @Provides
    public Gson providesGson() {
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
