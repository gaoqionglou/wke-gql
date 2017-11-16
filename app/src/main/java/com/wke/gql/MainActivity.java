package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.dagger2.component.DaggerNetComponent;
import com.wke.gql.net.BaseQuery;
import com.wke.gql.net.BaseQueryWithDagger;
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
    /**
     * 构造方法注入Inject
     * BaseQuery baseQuery; 成员变量不需要@Inject注解
     * baseQuery = DaggerBaseQueryComponent.builder().build().baseQueryInstance();
     */
    /**
     * 成员变量注入Inject
     * BaseQuery baseQuery; 成员变量需要@Inject注解
     * baseQuery = DaggerBaseQueryComponent.builder().build().inject(注入对象实例);
     * dagger2会扫描注入对象实例 所有打上@Inject注解的非私有成员变量 为其注入
     */
//   方法一  BaseQuery baseQuery;
//   方法一  BaseQueryWithDagger baseQueryWithDagger;
    @Inject
    BaseQuery baseQuery;
    @Inject
    BaseQueryWithDagger baseQueryWithDagger;
    Hint hint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DaggerNetComponent.builder().build().inject(this);

//      方法一  baseQuery = DaggerBaseQueryComponent.builder().build().baseQueryInstance();
        Log.i(TAG, "--------------baseQuery START-----------------");
        Log.i(TAG, "baseQuery" + baseQuery.toString());
        baseQuery.enqueue(RetrofitUtil.initRetrofitService(CityService.class).getAllCity("china"),this::querySuccess,this::queryFaild);
        baseQuery.enqueue(RetrofitUtil.initRetrofitService(CityService.class).getAllCity2("china"),this::querySuccess,this::queryFaild);
        new Thread(()->{
            hint = baseQuery.excute(RetrofitUtil.initRetrofitService(HintService.class).getHint());
            Log.i(TAG,hint.toString());
        }).start();
        Log.i(TAG, "--------------baseQuery END-----------------");

        Log.i(TAG, "--------------baseQueryWithDagger start-----------------");
//      方法一  baseQueryWithDagger = DaggerBaseQueryWithDaggerComponent.builder().build().baseQueryWithDaggerInstance();
        Log.i(TAG, "baseQueryWithDagger" + baseQueryWithDagger.toString());
        baseQueryWithDagger.enqueue(baseQueryWithDagger.initRetrofitService(CityService.class).getAllCity("china"), this::querySuccess, this::queryFaild);
        baseQueryWithDagger.enqueue(baseQueryWithDagger.initRetrofitService(CityService.class).getAllCity2("china"), this::querySuccess, this::queryFaild);
        new Thread(() -> {
            hint = baseQueryWithDagger.excute(baseQueryWithDagger.initRetrofitService(HintService.class).getHint());
            Log.i(TAG, hint.toString());
        }).start();
        Log.i(TAG, "--------------baseQueryWithDagger end-----------------");

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

    void querySuccess(List<City> cities){
        Log.i(TAG,"----"+cities.toString());
    }
    void queryFaild(Call<List<City>> call,Throwable t){

    }
}
