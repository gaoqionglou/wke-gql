package com.wke.gql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    AppCompatButton demo1, demo2, layoutDemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demo1 = (AppCompatButton) findViewById(R.id.demo1);
        demo2 = (AppCompatButton) findViewById(R.id.demo2);
        layoutDemo = (AppCompatButton) findViewById(R.id.layoutdemo);
    }

    public void toDemo1(View v) {
        ARouter.getInstance().build("/wkegql/demo").navigation();
    }

    public void toDemo2(View v) {
        ARouter.getInstance().build("/wkegql/weather").navigation();
    }

    public void toLayoutDemo(View v) {
        ARouter.getInstance().build("/wkegql/layoutdemo").navigation();
    }

    public void toCollapsingDemo(View v) {
        ARouter.getInstance().build("/wkegql/boot").navigation();
    }

    public void toCustomBehaviorLayoutDemo(View v) {
        ARouter.getInstance().build("/wkegql/layoutBehavior").navigation();
    }

    public void toFlowlayoutDemo(View v) {
        ARouter.getInstance().build("/wkegql/flowlayout").navigation();
    }

    public void toCustomTabDemo(View v) {
        ARouter.getInstance().build("/wkegql/customtab").navigation();
    }

    public void toShopViewdemo(View v) {
        ARouter.getInstance().build("/wkegql/shopview").navigation();
    }

    public void toCityWidgetdemo(View v) {
        ARouter.getInstance().build("/wkegql/citywidget").navigation();
    }
}
