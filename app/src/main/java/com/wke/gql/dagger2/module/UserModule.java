package com.wke.gql.dagger2.module;

import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.UserScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2017/11/16.
 */
@Module
public class UserModule {
    private User user;

    public UserModule(User user) {
        this.user = user;
    }

    @Provides
    @UserScope
    public User providesUser() {
        return user;
    }
}
