package com.wke.gql.MVPdagger2.testWeather.module;

import com.wke.gql.MVPdagger2.testWeather.WeatherContract;
import com.wke.gql.MVPdagger2.testWeather.WeatherPresenter;
import com.wke.gql.dagger2.di.PresenterScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Administrator on 2017/11/18.
 */
@Module
public abstract class WeatherPresenterMoudle {
    @PresenterScoped
    @Binds
    abstract WeatherContract.Presenter weatherPresenter(WeatherPresenter weatherPresenter);
}
