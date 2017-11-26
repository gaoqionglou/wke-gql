package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.wke.gql.MVPdagger2.testWeather.WeatherActivity;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    AppCompatButton demo1, demo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demo1 = (AppCompatButton) findViewById(R.id.demo1);
        demo2 = (AppCompatButton) findViewById(R.id.demo2);
    }

    public void toDemo1(View v) {
        Intent i = new Intent(this, DemoActivity.class);
        startActivityForResult(i, 100);
    }

    public void toDemo2(View v) {
        Intent i = new Intent(this, WeatherActivity.class);
        startActivityForResult(i, 100);
    }

}
