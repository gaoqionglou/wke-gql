package com.wke.gql.dagger2.module;

import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.net.NetWorkUtil;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by gql on 17-11-23.
 */
@Module
public class NetWorkUtilModule {

    Retrofit retrofit;

    public NetWorkUtilModule(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @ActivityScoped
    @Provides
    public NetWorkUtil providesRxNetWorkUtil() {
        return new NetWorkUtil(retrofit);
    }
}
