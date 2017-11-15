package com.wke.gql.dragger2.module;

import com.wke.gql.net.BaseQueryWithDagger;

import dagger.Module;
import dagger.Provides;

/**
 * Created by gql on 17-11-15.
 */
@Module
public class BaseQueryWithDaggerModule {
    @Provides
    public BaseQueryWithDagger providesBaseQueryWithDagger() {
        return new BaseQueryWithDagger();
    }
}
