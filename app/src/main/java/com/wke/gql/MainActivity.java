package com.wke.gql;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wke.gql.net.BaseQuery;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.RetrofitUtil;
import com.wke.gql.net.retrofit.CityService;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseQuery.enqueue(RetrofitUtil.initRetrofitService(CityService.class).getAllCity("china"),this::querySuccess,this::queryFaild);
    }

    void querySuccess(List<City> cities){

    }
    void queryFaild(){

    }
}
