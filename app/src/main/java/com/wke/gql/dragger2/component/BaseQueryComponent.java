package com.wke.gql.dragger2.component;


import com.wke.gql.MainActivity;
import com.wke.gql.dragger2.module.BaseQueryModule;

import dagger.Component;

@Component(modules = BaseQueryModule.class)
public interface BaseQueryComponent {
    void inject(MainActivity activity);
}
