package com.wke.gql.citywidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.R;

import java.util.List;

@Route(path = "/wkegql/citywidget")
public class CityWidgetActivity extends AppCompatActivity {
    private CityListIndexView cityListIndexView;
    private List<String> indexContents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_widget);
        cityListIndexView = (CityListIndexView) this.findViewById(R.id.indexview);
//        cityListIndexView.setIndexContents(indexContents);
    }
}
