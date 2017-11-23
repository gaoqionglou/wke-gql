package com.wke.gql.MVPdagger2.testWeather;

import android.util.Log;

import com.wke.gql.net.RxNetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.Hint;
import com.wke.gql.net.retrofit.RxHintService;

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
    public void loadWeather(RxNetWorkUtil rxNetWorkUtil) {
        if (mWeatherView != null) mWeatherView.refreshUi();
        rxNetWorkUtil.detchToView(mWeatherView).callBack(new RxNetWorkUtil.RxCallBack<Hint>() {
            @Override
            public void onPrepare() {
                Log.i(TAG, "onPrepare  -- ");
            }

            @Override
            public void onSuccess(Hint t) {
                Log.i(TAG, "onSuccess  -- " + t.toString());
            }

            @Override
            public void onFail(Throwable t) {
                Log.i(TAG, "onFail  -- " + t.getMessage());
            }
        }).doWork(rxNetWorkUtil.initRetrofitService(RxHintService.class).getHint());
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
