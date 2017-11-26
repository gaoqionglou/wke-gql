package com.wke.gql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.NetWorkUtil;
import com.wke.gql.net.RxNetWorkUtil;

public class BaseActivity extends AppCompatActivity {
    public RxNetWorkUtil rxNetWorkUtil;
    public NetWorkUtil netWorkUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxNetWorkUtil = ((BaseApplication) getApplication()).injectRxNetWorkUtilComponent().rxNetWorkUtil();
        netWorkUtil = ((BaseApplication) getApplication()).injectNetWorkUtilComponent().netWorkUtil();
    }
}
