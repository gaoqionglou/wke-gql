package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.dagger2.module.RxNetWorkUtilModule;
import com.wke.gql.net.RxNetWorkUtil;

import dagger.Subcomponent;

/**
 * Created by gql on 17-11-23.
 */
@ActivityScoped
@Subcomponent(modules = RxNetWorkUtilModule.class)
public interface RxNetWorkUtilComponent {
    RxNetWorkUtil rxNetWorkUtil();
}
