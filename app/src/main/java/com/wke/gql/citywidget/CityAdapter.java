package com.wke.gql.citywidget;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.wke.gql.R;
import com.wke.gql.greendao.bean.CityItem;

import java.util.List;

/**
 * Created by gql on 2018/3/11.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.CityViewHolder> implements StickyRecyclerHeadersAdapter<CityAdapter.CityIndexViewHolder> {
    private List<CityItem> cityItems;

    public CityAdapter(List<CityItem> cityItems) {
        this.cityItems = cityItems;
    }

    public List<CityItem> getCityItems() {
        return cityItems;
    }

    public void setCityItems(List<CityItem> cityItems) {
        this.cityItems = cityItems;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityItem item = cityItems.get(position);
        if ("1".equalsIgnoreCase(item.isDomestic)) {
            holder.textView.setText(item.cityCnName);
        } else {
            holder.textView.setText(item.cityCnName + "(" + item.getCityCode() + "/" + item.getCountryCnName() + ")");
        }
    }

    @Override
    public long getHeaderId(int position) {
        return cityItems.get(position).airportPinyinShort.substring(0, 1).charAt(0);
    }

    @Override
    public CityIndexViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_index, parent, false);
        return new CityIndexViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(CityIndexViewHolder holder, int position) {
        holder.textView.setText(cityItems.get(position).airportPinyinShort.substring(0, 1));
    }

    @Override
    public int getItemCount() {
        return cityItems.size();
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textView;

        public CityViewHolder(View itemView) {
            super(itemView);
            textView = (AppCompatTextView) itemView.findViewById(R.id.item_city_name);
        }
    }

    public class CityIndexViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textView;

        public CityIndexViewHolder(View itemView) {
            super(itemView);
            textView = (AppCompatTextView) itemView.findViewById(R.id.item_city_index);
        }
    }
}
