package com.wke.gql.dagger2.module;

import com.wke.gql.base.BaseApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/11/16.
 */
@Module
public class AppModule {
    BaseApplication baseApplication;

    public AppModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Singleton
    @Provides
    public BaseApplication providesBaseApplication() {
        return baseApplication;
    }
}
