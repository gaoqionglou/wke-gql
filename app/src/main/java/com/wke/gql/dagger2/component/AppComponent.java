package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.module.AppModule;
import com.wke.gql.dagger2.module.UserModule;
import com.wke.gql.dagger2.module.UtilModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    UserComponent plus(UserModule userModule);

    UtilComponent init(UtilModule utilModule);

    UtilComponent2 initUtilComponent2(UtilModule utilModule);
}
