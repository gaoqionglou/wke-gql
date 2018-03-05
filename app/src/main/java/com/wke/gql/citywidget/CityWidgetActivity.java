package com.wke.gql.citywidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.R;

import java.util.Arrays;
import java.util.List;

@Route(path = "/wkegql/citywidget")
public class CityWidgetActivity extends AppCompatActivity {
    private CityListIndexView cityListIndexView;
    private List<String> indexContents = Arrays.asList("GPS", "热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private TextView middleView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_widget);
        cityListIndexView = (CityListIndexView) this.findViewById(R.id.indexview);
        middleView = (TextView) this.findViewById(R.id.middleText);
        cityListIndexView.setPopUpView(middleView);
        cityListIndexView.setIndexContents(indexContents);
    }
}
