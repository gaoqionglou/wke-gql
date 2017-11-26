package com.wke.gql.dagger2.component;

import com.google.gson.Gson;
import com.wke.gql.base.BaseApplication;
import com.wke.gql.dagger2.module.AppMoudle;
import com.wke.gql.dagger2.module.NetWorkUtilModule;
import com.wke.gql.dagger2.module.RxNetWorkUtilModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = AppMoudle.class)
public interface AppComponent {
    BaseApplication baseApplication();

    Retrofit retrofit();

    Gson gson();

    RxNetWorkUtilComponent addSub(RxNetWorkUtilModule rxNetWorkUtilModule);

    NetWorkUtilComponent addSub(NetWorkUtilModule rxNetWorkUtilModule);
}
