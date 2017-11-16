package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.UserScope;
import com.wke.gql.dagger2.module.AModule;
import com.wke.gql.dagger2.module.BModule;
import com.wke.gql.dagger2.module.CModule;
import com.wke.gql.dagger2.module.UserModule;

import dagger.Subcomponent;

/**
 * Created by Administrator on 2017/11/16.
 */
@UserScope
@Subcomponent(modules = UserModule.class)
public interface UserComponent {
    AComponent plus(AModule aModule);

    BComponent plus(BModule bModule);

    CComponent plus(CModule cModule);
}
