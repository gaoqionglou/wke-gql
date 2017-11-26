package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wke.gql.MVPdagger2.testWeather.WeatherActivity;
import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.NetWorkUtil;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    NetWorkUtil netWorkUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        netWorkUtil = ((BaseApplication) getApplication()).injectNetWorkUtilComponent().netWorkUtil();
        Log.i(TAG, netWorkUtil.toString() + "||" + netWorkUtil.retrofit.toString());
    }

    public void goLogin(View v) {
        Intent i = new Intent(this, WeatherActivity.class);
        startActivityForResult(i, 100);
    }

}
