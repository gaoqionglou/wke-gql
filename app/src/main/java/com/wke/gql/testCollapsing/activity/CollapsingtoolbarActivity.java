package com.wke.gql.testCollapsing.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.wke.gql.R;
import com.wke.gql.testCollapsing.fragment.ConsolesMockListFragment;
import com.wke.gql.testCollapsing.fragment.GamesMockListFragment;
import com.wke.gql.testCollapsing.fragment.TextContentMockFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollapsingtoolbarActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<Fragment> fragments = Collections.emptyList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsingtoolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("");
        }
        setSupportActionBar(toolbar);

        populateFragmentList();

        FragmentsAdapter adapter = new FragmentsAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        if (viewPager != null) {
            viewPager.setAdapter(adapter);
        }

        tabLayout = (TabLayout) findViewById(R.id.content_tabs);
        if (tabLayout != null) {
            tabLayout.setupWithViewPager(viewPager);
        }

        setTabIndicatorIcons();
    }

    private void populateFragmentList() {
        fragments = new ArrayList<>();
        fragments.add(new TextContentMockFragment());
        fragments.add(new ConsolesMockListFragment());
        fragments.add(new GamesMockListFragment());
    }

    private void setTabIndicatorIcons() {
        //The tabs are fixed, let's just set the icons manually
        tabLayout.getTabAt(0).setIcon(android.R.drawable.btn_star_big_off);
        tabLayout.getTabAt(1).setIcon(android.R.drawable.star_big_on);
        tabLayout.getTabAt(2).setIcon(android.R.drawable.btn_star_big_on);
    }

    class FragmentsAdapter extends FragmentPagerAdapter {

        String tabTitles[] = new String[]{"Long Text", "Consoles", "Games"};
        List<Fragment> fragments = Collections.emptyList();

        public FragmentsAdapter(FragmentManager fm) {
            super(fm);
        }

        public void setFragments(List<Fragment> fragments) {
            this.fragments.clear();
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        /*
        Use this to set tab titles
        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
        */
    }

}
