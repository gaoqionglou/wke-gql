package com.wke.gql.MVPdagger2.testWeather.component;

import com.wke.gql.MVPdagger2.testWeather.WeatherActivity;
import com.wke.gql.MVPdagger2.testWeather.module.WeatherPresenterMoudle;

import dagger.Component;

/**
 * Created by Administrator on 2017/11/18.
 */
@Component(modules = WeatherPresenterMoudle.class)
public interface WeatherPresenterComponent {
    void inject(WeatherActivity weatherActivity);
}
