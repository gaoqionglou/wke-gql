package com.wke.gql.layoutdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.R;
import com.wke.gql.view.RippleTextViewLinearLayout;
import com.wke.gql.view.Slider;

@Route(path = "/wkegql/customtab")
public class CustomTabActivity extends AppCompatActivity {
    private Slider slider;
    private RippleTextViewLinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab);
        slider = (Slider) findViewById(R.id.slider);
        linearLayout = (RippleTextViewLinearLayout) findViewById(R.id.ll_ripple);
        slider.attacthTo(linearLayout);
    }
}
