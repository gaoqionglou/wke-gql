package com.wke.gql.dragger2.module;

import com.google.gson.Gson;
import com.wke.gql.dragger2.component.DaggerGsonComponent;
import com.wke.gql.utils.Contants;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gql on 17-11-15.
 */
@Module
public class RetrofitMoudle {
    @Inject
    Gson gson;

    public RetrofitMoudle() {
        this.gson = DaggerGsonComponent.builder().build().gsonInstance();
    }

    @Provides
    public Retrofit providesRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Contants.HTTP_QUERY_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
