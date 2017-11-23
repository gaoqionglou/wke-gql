package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.RxNetWorkUtil;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Inject
    RxNetWorkUtil rxNetWorkUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((BaseApplication) getApplication()).injectRxNetWorkUtilComponent().inject(this);
        Log.i(TAG, rxNetWorkUtil.toString() + "||" + rxNetWorkUtil.retrofit.toString());
    }

    public void goLogin(View v) {
        Intent i = new Intent(this, AActivity.class);
        startActivityForResult(i, 100);
    }

}
