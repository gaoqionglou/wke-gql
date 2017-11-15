package com.wke.gql.dragger2.component;

import com.wke.gql.MainActivity;
import com.wke.gql.dragger2.module.BaseQueryModule;
import com.wke.gql.dragger2.module.BaseQueryWithDaggerModule;

import dagger.Component;

/**
 * Created by gql on 17-11-15.
 */
@Component(modules = {BaseQueryWithDaggerModule.class, BaseQueryModule.class})
public interface NetComponent {
    void inject(MainActivity mainActivity);
}
