package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.NetWorkUtil;

public class AActivity extends AppCompatActivity {
    private static final String TAG = "AActivity";
    NetWorkUtil netWorkUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        netWorkUtil = ((BaseApplication) getApplication()).injectNetWorkUtilComponent().netWorkUtil();
        Log.i(TAG, netWorkUtil.toString() + "||" + netWorkUtil.retrofit.toString());
    }

    public void goB(View v) {
        startActivity(new Intent(this, BActivity.class));
    }
}
