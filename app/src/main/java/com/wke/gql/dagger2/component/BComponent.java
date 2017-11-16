package com.wke.gql.dagger2.component;

import com.wke.gql.BActivity;
import com.wke.gql.dagger2.module.BModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/11/16.
 */
@Subcomponent(modules = BModule.class)
public interface BComponent {
    void inject(BActivity bActivity);
}
