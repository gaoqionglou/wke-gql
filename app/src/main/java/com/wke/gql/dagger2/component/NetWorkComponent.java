package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.module.NetWorkModule;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/11/18.
 */
@Component(modules = NetWorkModule.class)
public interface NetWorkComponent {
    Retrofit retrofit();
}
