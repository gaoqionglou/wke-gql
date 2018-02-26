package com.wke.gql.customtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.BaseActivity;
import com.wke.gql.R;
import com.wke.gql.fragment.LazyLoadTabOneFragment;
import com.wke.gql.fragment.TabFourFragment;
import com.wke.gql.fragment.TabThreeFragment;
import com.wke.gql.fragment.TabTwoFragment;
import com.wke.gql.layoutdemo.activity.MyPagerAdapter;
import com.wke.gql.view.BottomBarLayout;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/wkegql/customtab2")
public class CustomTabActivity extends BaseActivity {
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private BottomBarLayout bottomBarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_tab2);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomBarLayout = (BottomBarLayout) findViewById(R.id.tab);
        bottomBarLayout.setViewPager(viewPager);
        bottomBarLayout.setSmoothScroll(true);
        fragmentList = new ArrayList<>();
        fragmentList.add(new LazyLoadTabOneFragment());
        fragmentList.add(new TabTwoFragment());
        fragmentList.add(new TabThreeFragment());
        fragmentList.add(new TabFourFragment());
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);
        bottomBarLayout.setCurrentTab(0);

        bottomBarLayout.notify(0);
        bottomBarLayout.notifyMsg(1, "NEWWWW");
        bottomBarLayout.notifyNumber(2, 9);
        bottomBarLayout.notifyNumber(3, 123);
    }
}
