package com.wke.gql.MVPdagger2.testWeather;

import android.util.Log;

import com.wke.gql.net.NetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.Hint;
import com.wke.gql.net.retrofit.HintService;

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
    public void loadWeather(NetWorkUtil netWorkUtil) {
        if (mWeatherView != null) mWeatherView.refreshUi();
        netWorkUtil.detchToView(mWeatherView).callBack(netWorkUtil.new CommonCallBack<Hint>() {
            @Override
            protected void dealWithSuccess(Hint o) {
                Log.i(TAG, o.toString());
            }

            @Override
            protected void dealWithFail(Call call, Throwable t) {
                Log.e(TAG, t.toString());
            }

        }).enqueue(netWorkUtil.initRetrofitService(HintService.class).getHint());
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
