package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.dagger2.module.NetWorkUtilModule;
import com.wke.gql.net.NetWorkUtil;

import dagger.Subcomponent;


@ActivityScoped
@Subcomponent(modules = NetWorkUtilModule.class)
public interface NetWorkUtilComponent {
    NetWorkUtil netWorkUtil();
//    void inject(MainActivity activity);
//    void inject(WeatherActivity activity);
//    void inject(AActivity activity);
//
//    void inject(BActivity activity);
}
