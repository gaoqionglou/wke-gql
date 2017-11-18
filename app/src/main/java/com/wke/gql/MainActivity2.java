package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.NetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.CityService;
import com.wke.gql.net.retrofit.Hint;
import com.wke.gql.net.retrofit.HintService;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class MainActivity2 extends BaseActivity {
    private static final String TAG = "MainActivity2";

    @Inject
    NetWorkUtil netWorkUtil;

    Hint hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseApplication.getApplication().createUtilComponent().inject(this);
        Log.i(TAG, "onCreate: " + netWorkUtil.toString());
        netWorkUtil.enqueue(netWorkUtil.initRetrofitService(CityService.class).getAllCity("china"), this::querySuccess, this::queryFaild);
        netWorkUtil.enqueue(netWorkUtil.initRetrofitService(CityService.class).getAllCity2("china"), this::querySuccess, this::queryFaild);
        new Thread(() -> {
            hint = netWorkUtil.excute(netWorkUtil.initRetrofitService(HintService.class).getHint());
            Log.i(TAG, hint.toString());
        }).start();

    }

    public void goLogin(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("hint", hint);
        startActivityForResult(i, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == 100) {
            String hint = data.getStringExtra("hint");
            TextView textView = (TextView) findViewById(R.id.hint_text);
            textView.setText(hint);
        }
    }

    void querySuccess(List<City> cities) {
        Log.i(TAG, "----" + cities.toString());
    }

    void queryFaild(Call<List<City>> call, Throwable t) {

    }
}
