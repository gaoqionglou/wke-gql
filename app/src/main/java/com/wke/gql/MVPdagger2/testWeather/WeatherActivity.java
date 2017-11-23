package com.wke.gql.MVPdagger2.testWeather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wke.gql.R;
import com.wke.gql.net.RxNetWorkUtil;
import com.wke.gql.utils.DialogUtil;
import com.wke.gql.view.LoadingDialog;

public class WeatherActivity extends AppCompatActivity implements WeatherContract.View {
    //    @Inject
    WeatherContract.Presenter weatherPresenter;
    //    @Inject
    RxNetWorkUtil rxNetWorkUtil;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        weatherPresenter.takeView(this);
        weatherPresenter.loadWeather(rxNetWorkUtil);
    }

    @Override
    protected void onResume() {
        weatherPresenter.takeView(this);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        weatherPresenter.dropView();
        super.onDestroy();
    }

    @Override
    public void refreshUi() {

    }


    @Override
    public void showLoading() {
//        loadingDialog = DialogUtil.showLoadingDialog(this);
        loadingDialog = DialogUtil.showLoadingDialog(this, true, () -> {
            Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null) loadingDialog.dismissAllowingStateLoss();
    }
}
