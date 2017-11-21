package com.wke.gql.MVPdagger2.testWeather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wke.gql.MVPdagger2.testWeather.component.DaggerWeatherPresenterComponent;
import com.wke.gql.R;

import javax.inject.Inject;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {
    @Inject
    WeatherContract.Presenter weatherPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        DaggerWeatherPresenterComponent.builder().build().inject(this);
        weatherPresenter.takeView(this);
        weatherPresenter.loadWeather();
        weatherPresenter.loadWeather();
    }

    @Override
    protected void onResume() {
        weatherPresenter.takeView(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        weatherPresenter.dropView();
        super.onDestroy();
    }

    @Override
    public void refreshUi() {

    }


    @Override
    public void showLoading() {

    }

    @Override
    public void dismissLoading() {

    }
}
