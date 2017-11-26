package com.wke.gql;

import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;

import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.CityService;
import com.wke.gql.utils.DialogUtil;
import com.wke.gql.view.LoadingDialog;

import java.util.List;

import retrofit2.Call;

public class DemoActivity extends BaseActivity implements BaseView {
    private static final String TAG = "DemoActivity";
    private LoadingDialog loadingDialog;
    private AppCompatTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        textView = (AppCompatTextView) findViewById(R.id.text);
        netWorkUtil.detchToView(this).callBack(netWorkUtil.new CommonCallBack<List<City>>() {

            @Override
            protected void dealWithSuccess(List<City> o) {
                Log.i(TAG, "dealWithSuccess: " + o.toString());
                textView.setText(o.toString());

            }

            @Override
            protected void dealWithFail(Call call, Throwable t) {

            }
        }).enqueue(netWorkUtil.initRetrofitService(CityService.class).getAllCity("china"));
    }

    @Override
    public void showLoading() {
        loadingDialog = DialogUtil.showLoadingDialog(this);
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null) loadingDialog.dismissAllowingStateLoss();
    }
}
