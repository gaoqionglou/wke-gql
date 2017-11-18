package com.wke.gql.dagger2.component;

import com.wke.gql.MainActivity2;
import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.dagger2.module.UtilModule;

import dagger.Subcomponent;


@ActivityScoped
@Subcomponent(modules = UtilModule.class)
public interface UtilComponent {
    void inject(MainActivity2 activity);
}
