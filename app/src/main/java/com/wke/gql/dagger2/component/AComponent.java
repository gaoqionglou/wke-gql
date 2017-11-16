package com.wke.gql.dagger2.component;

import com.wke.gql.AActivity;
import com.wke.gql.dagger2.module.AModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/11/16.
 */
@Subcomponent(modules = AModule.class)
public interface AComponent {
    void inject(AActivity aActivity);
}
