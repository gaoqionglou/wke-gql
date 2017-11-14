package com.wke.gql.dragger2.module;

import com.wke.gql.net.BaseQuery;

import dagger.Module;
import dagger.Provides;


@Module
public class BaseQueryModule {
    @Provides
    public BaseQuery providesBaseQuery() {
        return new BaseQuery();
    }
}
