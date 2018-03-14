package com.wke.gql.citywidget;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.wke.gql.R;
import com.wke.gql.greendao.bean.CityItem;
import com.wke.gql.greendao.bean.HistoryCityItem;
import com.wke.gql.greendao.gen.CityItemDao;
import com.wke.gql.utils.BeanUtils;
import com.wke.gql.utils.UtilTool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Route(path = "/wkegql/citywidget2")
public class CityWidgetActivity2 extends AppCompatActivity {
    private static final String TAG = "CityWidgetActivity";
    private CityListIndexView cityListIndexView;
    private TextView middleView;
    private TabLayout tabLayout;
    private RecyclerView rv;
    private StickyRecyclerHeadersDecoration headersDecor;
    private List<CityItem> items;
    private boolean domestic = true;
    private boolean isChinese = true;
    private CityAdapter2 cityAdapter;

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
                    cityAdapter.setCityData(getData2(items, "1"));
                    cityAdapter.notifyDataSetChanged();
                } else if ("国际".equalsIgnoreCase(tab.getText().toString())) {
                    items = CityItem.queryBuilder(CityItem.class).where(CityItemDao.Properties.IsDomestic.eq("0")).list();
                    cityAdapter.setCityData(getData2(items, "0"));
                    cityAdapter.notifyDataSetChanged();
                }
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
        List<CityData> datas = getData2(items, "1");
        Collections.sort(items, new Comparator<CityItem>() {
            @Override
            public int compare(CityItem o1, CityItem o2) {
                return o1.airportPinyinShort.substring(0, 1).compareTo(o2.airportPinyinShort.substring(0, 1));
            }
        });
        getIndexList(items);
        cityAdapter = new CityAdapter2(this, datas);
        //装载头部！
        headersDecor = new StickyRecyclerHeadersDecoration(cityAdapter); //绑定之前的adapter
        //刷新数据的时候回刷新头部
        cityAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });
        rv.addItemDecoration(headersDecor);
        rv.setLayoutManager(new LinearLayoutManager(this));
        //建立index和rv的联系
        cityListIndexView.attachToCityList(rv);
        cityAdapter.setIndexView(cityListIndexView);
        //列表的点击事件 包括index
        cityAdapter.setOnItemClickListener(new CityAdapter2.OnItemClickListener() {
            @Override
            public void onItemClick(CityItem cityItem) {
                Log.i(TAG, "onItemClick: " + cityItem.cityCnName);
                HistoryCityItem.addHistoryCity(cityItem);
            }

            @Override
            public void onItemIndexClick(String index) {
                Log.i(TAG, "onItemIndexClick: " + index);
            }
        });
        //列表的滑动事件
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    String index = cityAdapter.getCityData().get(firstVisibleItemPosition).index;
                    cityListIndexView.setIndexHighLight(index);
                    Log.i(TAG, "index: " + index);
                }
            }

            /**
             * @param recyclerView
             * @param dx           水平滚动距离
             * @param dy           垂直滚动距离
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
        rv.setAdapter(cityAdapter);
        //index的点击事件
        cityListIndexView.setOnIndexTextClickCallBack(new CityListIndexView.OnIndexTextClickCallBack() {
            @Override
            public void onTouch(View v, MotionEvent ev) {

            }

            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: " + v.getTag().toString());
                scrollToIndex(v.getTag().toString());
            }
        });
        //index的滑动事件
        cityListIndexView.setOnScrollListener(new CityListIndexView.OnScrollListener() {
            @Override
            public void onScroll(View v, String index, int position) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) rv.getLayoutManager();
                int p = layoutManager.findFirstVisibleItemPosition();
                Log.i(TAG, "onScroll: " + index + "," + p);
                if (cityAdapter.getCityData().get(p).index.equalsIgnoreCase(index)) return;
                scrollToIndex(index);
            }
        });
    }

    private void scrollToIndex(String index) {
        int a = 0;
        //下面是对字母的处理
        for (int i = 0; i < cityAdapter.getCityData().size(); i++) {

            if (index.equalsIgnoreCase(cityAdapter.getCityData().get(i).index)) {
                a = i;
                break;
            }
        }
        //针对这2个特殊处理
        if (index.equalsIgnoreCase("GPS/历史")) {
            a = 0;
        }
        if (index.equalsIgnoreCase("热门")) {
            a = 1;
        }
        smoothMoveToPosition(rv, a);
    }

    private void smoothMoveToPosition(RecyclerView mRecyclerView, int position) {
        // 第一个可见位置
        int firstItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        int lastItem = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        if (position < firstItem) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.scrollToPosition(position);
        } else if (position <= lastItem) {
            // 第二种可能:跳转位置在第一个可见位置之后
            int movePosition = position - firstItem;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                //减去index的高度
                mRecyclerView.scrollBy(0, top - UtilTool.dip2px(20));
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.scrollToPosition(position);
        }
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
        indexList.add(0, "热门");
        indexList.add(0, "历史");
        indexList.add(0, "GPS");
        cityListIndexView.setIndexContents(indexList);
        Log.i(TAG, "getIndexList: " + indexList.toString());
    }


    private List<CityData> getData2(List<CityItem> cityItems, String isDomestic) {
        List<CityData> data = new ArrayList<>();
        try {
            List<HistoryCityItem> historys = HistoryCityItem.findAll(HistoryCityItem.class);
            List<CityItem> historyCityItemList = new ArrayList<>();
            for (HistoryCityItem history : historys) {
                historyCityItemList.add(BeanUtils.convert2CityItem(history));
            }


            CityData historyData = new CityData();
            historyData.index = "GPS/历史";
            historyData.itemList = historyCityItemList;
            CityItem gpsItem = cityItems.get(0);
            gpsItem.isGPS = true;
            historyData.itemList.add(0, gpsItem);

            CityData hotData = new CityData();
            hotData.index = "热门";
            hotData.itemList =
                    CityItem.queryBuilder(CityItem.class).where(CityItemDao.Properties.IsDomestic.eq(isDomestic), CityItemDao.Properties.IsHot.eq("1")).list();
            ;


            for (CityItem item : cityItems) {
                CityData d = new CityData();
                d.index = item.airportPinyinShort.substring(0, 1).toUpperCase();
                d.itemList = new ArrayList<CityItem>();
                d.itemList.add(item);
                data.add(d);
            }

            //先排序字母类
            Collections.sort(data, new Comparator<CityData>() {
                @Override
                public int compare(CityData o1, CityData o2) {
                    return o1.index.compareTo(o2.index);
                }
            });

            data.add(0, hotData);
            data.add(0, historyData);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

}
