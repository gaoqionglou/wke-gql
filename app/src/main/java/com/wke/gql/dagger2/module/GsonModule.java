package com.wke.gql.dagger2.module;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gql on 17-11-15.
 */
@Module
public class GsonModule {
    private static final String TAG = "GsonModule";
    @Provides
    public Gson providesGson() {
        Log.i(TAG, "providesGson: ");
        return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }
}
