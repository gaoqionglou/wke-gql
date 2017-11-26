package com.wke.gql.MVPdagger2.testWeather;

import android.util.Log;

import com.wke.gql.net.RxNetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.RxCityService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class WeatherPresenter implements WeatherContract.Presenter {
    private static final String TAG = "WeatherPresenter";

    private WeatherContract.View mWeatherView;

    @Inject
    public WeatherPresenter() {
    }

    @Override
    public void loadWeather(RxNetWorkUtil netWorkUtil) {
        netWorkUtil.detchToView(mWeatherView).callBack(new RxNetWorkUtil.RxCallBack<List<City>>() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onSuccess(List<City> cities) {
                if (mWeatherView != null) mWeatherView.refreshUi(cities);
            }

            @Override
            public void onFail(Throwable t) {

            }
        }).doWork(netWorkUtil.initRetrofitService(RxCityService.class).getAllCity("china"));
    }

    @Override
    public void takeView(WeatherContract.View view) {
        this.mWeatherView = view;
    }

    @Override
    public void dropView() {
        Log.i(TAG, "dropView: ");
        this.mWeatherView = null;
    }

    private void querySuccess(List<City> cities) {
        Log.i(TAG, "----" + cities.toString());
    }

    private void queryFaild(Call<List<City>> call, Throwable t) {
    }
}
