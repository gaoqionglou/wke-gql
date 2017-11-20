package com.wke.gql.dagger2.module;

import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.net.NetWorkUtil;
import com.wke.gql.net.RxNetWorkUtil;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/11/18.
 */
@Module
public class UtilModule {
    @ActivityScoped
    @Provides
    public NetWorkUtil providesNetWorkUtil() {
        return new NetWorkUtil();
    }

    @ActivityScoped
    @Provides
    public RxNetWorkUtil providesRxNetWorkUtil() {
        return new RxNetWorkUtil();
    }
}
