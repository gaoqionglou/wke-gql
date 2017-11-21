package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.dagger2.module.UtilModule;
import com.wke.gql.net.NetWorkUtil;
import com.wke.gql.net.RxNetWorkUtil;

import dagger.Subcomponent;


@ActivityScoped
@Subcomponent(modules = UtilModule.class)
public interface UtilComponent2 {
    RxNetWorkUtil rxNetWorkUtil();

    NetWorkUtil netWorkUtil();
}
