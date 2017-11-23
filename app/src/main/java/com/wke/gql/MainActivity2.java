package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wke.gql.MVPdagger2.testWeather.WeatherActivity;
import com.wke.gql.net.retrofit.Hint;

public class MainActivity2 extends BaseActivity {
    private static final String TAG = "MainActivity2";


    Hint hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void goLogin(View v) {
        Intent i = new Intent(this, WeatherActivity.class);
        i.putExtra("hint", hint);
        startActivityForResult(i, 100);
    }
}
