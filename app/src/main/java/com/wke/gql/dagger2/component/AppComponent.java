package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.module.AppModule;
import com.wke.gql.dagger2.module.UserModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2017/11/16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    UserComponent plus(UserModule userModule);
}
