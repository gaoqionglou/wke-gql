package com.wke.gql.dagger2.component;

import com.wke.gql.dagger2.module.RetrofitMoudle;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = RetrofitMoudle.class)
public interface RetrofitComponent {
    Retrofit retrofitInstance();
}
