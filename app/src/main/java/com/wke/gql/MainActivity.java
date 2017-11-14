package com.wke.gql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wke.gql.dragger2.component.DaggerBaseQueryComponent;
import com.wke.gql.net.BaseQuery;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.CityService;
import com.wke.gql.net.retrofit.Hint;
import com.wke.gql.net.retrofit.HintService;
import com.wke.gql.net.retrofit.RetrofitUtil;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    BaseQuery baseQuery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerBaseQueryComponent.builder().build().inject(this);
        baseQuery.enqueue(RetrofitUtil.initRetrofitService(CityService.class).getAllCity("china"),this::querySuccess,this::queryFaild);
        baseQuery.enqueue(RetrofitUtil.initRetrofitService(CityService.class).getAllCity2("china"),this::querySuccess,this::queryFaild);
        new Thread(()->{
            Hint hint =  baseQuery.excute(RetrofitUtil.initRetrofitService(HintService.class).getHint());
            Log.i(TAG,hint.toString());
        }).start();

    }

    void querySuccess(List<City> cities){
        Log.i(TAG,"----"+cities.toString());
    }
    void queryFaild(Call<List<City>> call,Throwable t){

    }
}
