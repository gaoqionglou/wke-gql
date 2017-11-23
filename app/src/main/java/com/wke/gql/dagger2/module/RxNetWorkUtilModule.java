package com.wke.gql.dagger2.module;

import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.net.RxNetWorkUtil;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by gql on 17-11-23.
 */
@Module
public class RxNetWorkUtilModule {

    Retrofit retrofit;

    public RxNetWorkUtilModule(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @ActivityScoped
    @Provides
    public RxNetWorkUtil providesRxNetWorkUtil() {
        return new RxNetWorkUtil(retrofit);
    }
}
