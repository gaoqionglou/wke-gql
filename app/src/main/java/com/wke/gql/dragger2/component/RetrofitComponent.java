package com.wke.gql.dragger2.component;

import com.wke.gql.dragger2.module.RetrofitMoudle;

import dagger.Component;
import retrofit2.Retrofit;


@Component(modules = RetrofitMoudle.class)
public interface RetrofitComponent {
    Retrofit retrofitInstance();
}
