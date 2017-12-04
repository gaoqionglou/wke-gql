package com.wke.gql.layoutdemo.activity;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.BaseActivity;
import com.wke.gql.R;

@Route(path = "/wkegql/layoutBehavior")
public class LayoutBehaviorDemoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_behavior_demo);
    }
}
