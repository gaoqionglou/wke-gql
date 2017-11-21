package com.wke.gql.MVPdagger2.testWeather;

import android.util.Log;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.RxNetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.RxCityService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class WeatherPresenter implements WeatherContract.Presenter {
    private static final String TAG = "WeatherPresenter";
    RxNetWorkUtil rxNetWorkUtil;

    private WeatherContract.View mWeatherView;

    @Inject
    public WeatherPresenter() {
        rxNetWorkUtil = BaseApplication.getApplication().createUtilComponent2().rxNetWorkUtil();
    }

    @Override
    public void loadWeather() {
        if (mWeatherView != null) mWeatherView.refreshUi();
        rxNetWorkUtil.detchToView(mWeatherView).callBack(new RxNetWorkUtil.RxCallBack<List<City>>() {
            @Override
            public void onPrepare() {
                Log.i(TAG, "onPrepare  -- ");
            }

            @Override
            public void onSuccess(List<City> t) {
                Log.i(TAG, "onSuccess  -- " + t.toString());
            }

            @Override
            public void onFail(Throwable t) {
                Log.i(TAG, "onFail  -- " + t.getMessage());
            }
        }).doWork(rxNetWorkUtil.initRetrofitService(RxCityService.class).getAllCity("china"));
//        Log.i(TAG, baseQueryWithDagger.callList.size() + "  || " + baseQueryWithDagger.callList.toString());
//        baseQueryWithDagger.enqueue(baseQueryWithDagger.initRetrofitService(CityService.class).getAllCity("china"), this::querySuccess, this::queryFaild);
    }

    @Override
    public void takeView(WeatherContract.View view) {
        this.mWeatherView = view;
    }

    @Override
    public void dropView() {
        Log.i(TAG, "dropView: ");
        this.mWeatherView = null;
//        baseQueryWithDagger.cancel();
    }

    private void querySuccess(List<City> cities) {
        Log.i(TAG, "----" + cities.toString());
    }

    private void queryFaild(Call<List<City>> call, Throwable t) {
    }
}
