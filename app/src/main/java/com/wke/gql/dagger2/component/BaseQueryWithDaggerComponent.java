package com.wke.gql.dagger2.component;


import com.wke.gql.dagger2.module.BaseQueryWithDaggerModule;
import com.wke.gql.net.BaseQueryWithDagger;

import dagger.Component;

@Component(modules = BaseQueryWithDaggerModule.class)
public interface BaseQueryWithDaggerComponent {
    BaseQueryWithDagger baseQueryWithDaggerInstance();
}
