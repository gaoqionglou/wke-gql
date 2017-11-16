package com.wke.gql.dagger2.component;

import com.wke.gql.CActivity;
import com.wke.gql.dagger2.module.CModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/11/16.
 */
@Subcomponent(modules = CModule.class)
public interface CComponent {
    void inject(CActivity aActivity);
}
