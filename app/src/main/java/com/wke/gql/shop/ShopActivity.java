package com.wke.gql.shop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.R;

@Route(path = "/wkegql/shopview")
public class ShopActivity extends AppCompatActivity {
    private static final String TAG = "ShopActivity";
    private ShopView shopView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        shopView = (ShopView) this.findViewById(R.id.shopview);
        shopView.setOnButtonClickCallBack(new ShopView.OnButtonClickCallBack() {
            @Override
            public void onDel(int num) {
                Log.i(TAG, "onDel: " + num);
            }

            @Override
            public void onAdd(int num) {
                Log.i(TAG, "onAdd: " + num);
            }

            @Override
            public void onShowButton() {
                Log.i(TAG, "onShowButton: ");
            }

            @Override
            public void onHideButton() {
                Log.i(TAG, "onHideButton: ");
            }
        });
    }
}
