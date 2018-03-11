package com.wke.gql.citywidget;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.wke.gql.R;
import com.wke.gql.greendao.bean.CityItem;
import com.wke.gql.greendao.gen.CityItemDao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(path = "/wkegql/citywidget")
public class CityWidgetActivity extends AppCompatActivity {
    private static final String TAG = "CityWidgetActivity";
    private CityListIndexView cityListIndexView;
    private TextView middleView;
    private TabLayout tabLayout;
    private RecyclerView rv;
    private StickyRecyclerHeadersDecoration headersDecor;
    private List<CityItem> items;
    private boolean domestic = true;
    private boolean isChinese = true;
    private CityAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_widget);
        cityListIndexView = (CityListIndexView) this.findViewById(R.id.indexview);
        middleView = (TextView) this.findViewById(R.id.middleText);
        tabLayout = (TabLayout) this.findViewById(R.id.tablayout);
        rv = (RecyclerView) this.findViewById(R.id.rv_city);
        cityListIndexView.setPopUpView(middleView);

        tabLayout.addTab(tabLayout.newTab().setText("国内"), true);
        tabLayout.addTab(tabLayout.newTab().setText("国际"), false);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if ("国内".equalsIgnoreCase(tab.getText().toString())) {
                    items = CityItem.queryBuilder(CityItem.class).where(CityItemDao.Properties.IsDomestic.eq("1")).list();
                    cityAdapter.setCityItems(items);
                    cityAdapter.notifyDataSetChanged();
                } else if ("国际".equalsIgnoreCase(tab.getText().toString())) {
                    items = CityItem.queryBuilder(CityItem.class).where(CityItemDao.Properties.IsDomestic.eq("0")).list();
                    cityAdapter.setCityItems(items);
                    cityAdapter.notifyDataSetChanged();
                }
                Toast.makeText(CityWidgetActivity.this, tab.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        getData();

    }


    private void getData() {
        items = CityItem.queryBuilder(CityItem.class).where(CityItemDao.Properties.IsDomestic.eq("1")).list();
        Collections.sort(items, new Comparator<CityItem>() {
            @Override
            public int compare(CityItem o1, CityItem o2) {
                return o1.airportPinyinShort.substring(0, 1).compareTo(o2.airportPinyinShort.substring(0, 1));
            }
        });
        getIndexList(items);
        cityAdapter = new CityAdapter(items);
        headersDecor = new StickyRecyclerHeadersDecoration(cityAdapter); //绑定之前的adapter
        cityAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });  //刷新数据的时候回刷新头部
        rv.addItemDecoration(headersDecor);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(cityAdapter);
        Log.i(TAG, "getIndexList: " + items.toString());

    }

    private void getIndexList(List<CityItem> items) {
        Set<String> indexs = new HashSet<>();
        for (CityItem item : items) {
            indexs.add(isChinese ? item.airportPinyinShort.substring(0, 1) : item.airportEnNameSimple.substring(0, 1));
        }
        List<String> indexList = new ArrayList<>(indexs);
        Collections.sort(indexList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        cityListIndexView.setIndexContents(indexList);
        Log.i(TAG, "getIndexList: " + indexList.toString());
    }
}
