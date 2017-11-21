package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.net.RxNetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.Hint;
import com.wke.gql.net.retrofit.RxCityService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class MainActivity2 extends BaseActivity {
    private static final String TAG = "MainActivity2";

    @Inject
    RxNetWorkUtil rxNetWorkUtil;

    Hint hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BaseApplication.getApplication().createUtilComponent().inject(this);
        Log.i(TAG, "onCreate: " + rxNetWorkUtil.toString());
        RxCityService rxCityService = rxNetWorkUtil.initRetrofitService(RxCityService.class);
        Observable<List<City>> observable = rxCityService.getAllCity("china");
//        OnNext和doOnNext都是请求成功后调用。当请求成功后会先调用doOnNext中保存用户信息的方法，然后才去执行OnNext中的方法。若请求失败，则不会调用doOnNext和OnNext中的方法
        observable.subscribeOn(Schedulers.io())
                .doOnNext(cities -> {
                    Log.i(TAG, "rx java doOnNext - " + Thread.currentThread().getName() + "- " + cities.toString());
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cities -> {
                    Log.i(TAG, "rx java subscribe- " + Thread.currentThread().getName() + "- " + cities.toString());
                });

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
