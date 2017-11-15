package com.wke.gql.dragger2.component;


import com.wke.gql.dragger2.module.BaseQueryModule;
import com.wke.gql.net.BaseQuery;

import dagger.Component;

@Component(modules = BaseQueryModule.class)
public interface BaseQueryComponent {
    BaseQuery baseQueryInstance();
}
