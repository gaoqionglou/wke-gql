package com.wke.gql.layoutdemo.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.BaseActivity;
import com.wke.gql.R;
import com.wke.gql.fragment.LazyLoadTabOneFragment;
import com.wke.gql.fragment.TabThreeFragment;
import com.wke.gql.fragment.TabTwoFragment;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/wkegql/layoutBehavior")
public class LayoutBehaviorDemoActivity extends BaseActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_behavior_demo);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        fragmentList.add(new LazyLoadTabOneFragment());
        fragmentList.add(new TabTwoFragment());
        fragmentList.add(new TabThreeFragment());
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
