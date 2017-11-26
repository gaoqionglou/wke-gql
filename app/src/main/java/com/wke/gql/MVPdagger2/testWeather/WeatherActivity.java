package com.wke.gql.MVPdagger2.testWeather;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wke.gql.BaseActivity;
import com.wke.gql.MVPdagger2.testWeather.component.DaggerWeatherPresenterComponent;
import com.wke.gql.R;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.utils.DialogUtil;
import com.wke.gql.view.LoadingDialog;

import java.util.List;

import javax.inject.Inject;

public class WeatherActivity extends BaseActivity implements WeatherContract.View {
    private static final String TAG = "WeatherActivity";
    @Inject
    WeatherContract.Presenter weatherPresenter;


    private LoadingDialog loadingDialog;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private CityAdapter cityAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
        DaggerWeatherPresenterComponent.builder().build().inject(this);
        weatherPresenter.takeView(this);
        weatherPresenter.loadWeather(rxNetWorkUtil);
    }

    void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.rv_city_list);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
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
    public void refreshUi(List<City> cities) {
        Log.i(TAG, cities.toString());
        cityAdapter = new CityAdapter(cities);
        recyclerView.setAdapter(cityAdapter);
    }


    @Override
    public void showLoading() {
        loadingDialog = DialogUtil.showLoadingDialog(this, true, () -> {
            Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void dismissLoading() {
        if (loadingDialog != null) loadingDialog.dismissAllowingStateLoss();
    }


    public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
        public List<City> cities = null;

        public CityAdapter(List<City> cities) {
            this.cities = cities;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.mTextView.setText(cities.get(position).name);
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return cities.size();
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = (TextView) view.findViewById(R.id.item_city_name);
            }
        }
    }

}
