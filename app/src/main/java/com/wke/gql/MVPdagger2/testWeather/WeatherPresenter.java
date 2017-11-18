package com.wke.gql.MVPdagger2.testWeather;

import android.support.annotation.Nullable;
import android.util.Log;

import com.wke.gql.dagger2.component.DaggerBaseQueryWithDaggerComponent;
import com.wke.gql.net.BaseQueryWithDagger;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.CityService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class WeatherPresenter implements WeatherContract.Presenter {
    private static final String TAG = "WeatherPresenter";
    BaseQueryWithDagger baseQueryWithDagger;
    @Nullable
    private WeatherContract.View mWeatherView;

    @Inject
    public WeatherPresenter() {
        baseQueryWithDagger = DaggerBaseQueryWithDaggerComponent.builder().build().baseQueryWithDaggerInstance();
    }

    @Override
    public void loadWeather() {
        if (mWeatherView != null) mWeatherView.refreshUi();
        Log.i(TAG, baseQueryWithDagger.callList.size() + "  || " + baseQueryWithDagger.callList.toString());
        baseQueryWithDagger.enqueue(baseQueryWithDagger.initRetrofitService(CityService.class).getAllCity("china"), this::querySuccess, this::queryFaild);
    }

    @Override
    public void takeView(WeatherContract.View view) {
        this.mWeatherView = view;
    }

    @Override
    public void dropView() {
        Log.i(TAG, "dropView: ");
        this.mWeatherView = null;
        baseQueryWithDagger.cancel();
    }

    private void querySuccess(List<City> cities) {
        Log.i(TAG, "----" + cities.toString());
    }

    private void queryFaild(Call<List<City>> call, Throwable t) {
    }
}
