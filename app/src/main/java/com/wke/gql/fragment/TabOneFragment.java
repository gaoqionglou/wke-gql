package com.wke.gql.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wke.gql.BaseActivity;
import com.wke.gql.BaseView;
import com.wke.gql.R;
import com.wke.gql.net.RxNetWorkUtil;
import com.wke.gql.net.retrofit.City;
import com.wke.gql.net.retrofit.RxCityService;

import java.util.List;

/**
 * Created by gql on 17-11-30.
 */

public class TabOneFragment extends Fragment {
    private static final String TAG = "TabOneFragment";
    private View view;
    private RecyclerView rv;
    private LinearLayoutManager layoutManager;
    private List<City> cities;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tabone, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (view == null) return;
        rv = (RecyclerView) view.findViewById(R.id.fragment_tabone_rv);
        layoutManager = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        ((BaseActivity) getActivity()).rxNetWorkUtil.detchToView((BaseView) getActivity()).callBack(new RxNetWorkUtil.RxCallBack<List<City>>() {
            @Override
            public void onPrepare() {

            }

            @Override
            public void onSuccess(List<City> cities) {
                rv.setAdapter(new TabOneAdapter(cities));
            }

            @Override
            public void onFail(Throwable t) {

            }
        }).doWork(((BaseActivity) getActivity()).rxNetWorkUtil.initRetrofitService(RxCityService.class).getAllCity("china"));
    }


    class TabOneAdapter extends RecyclerView.Adapter<TabOneViewHolder> {

        private List<City> cities;

        public TabOneAdapter(List<City> cities) {
            this.cities = cities;
        }

        @Override
        public TabOneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
            return new TabOneViewHolder(view);
        }

        @Override
        public void onBindViewHolder(TabOneViewHolder holder, int position) {
            holder.tv.setText(cities.get(position).name);
        }

        @Override
        public int getItemCount() {
            return cities.size();
        }
    }

    class TabOneViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public TabOneViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_city_name);
        }
    }
}
