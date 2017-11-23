package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.RxNetWorkUtil;

import javax.inject.Inject;

public class BActivity extends AppCompatActivity {
    private static final String TAG = "BActivity";
    @Inject
    RxNetWorkUtil rxNetWorkUtil;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        ((BaseApplication) getApplication()).injectRxNetWorkUtilComponent().inject(this);
        Log.i(TAG, rxNetWorkUtil.toString() + "||" + rxNetWorkUtil.retrofit.toString());

    }

    public void goC(View v) {
        startActivity(new Intent(this, CActivity.class));
    }
}
