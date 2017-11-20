package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.module.RxNetWorkModule;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/11/18.
 */
@Component(modules = RxNetWorkModule.class)
public interface RxNetWorkComponent {
    Retrofit retrofit();
}
