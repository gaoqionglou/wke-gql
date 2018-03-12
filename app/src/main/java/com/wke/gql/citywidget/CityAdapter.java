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
    private OnItemClickListener onItemClickListener;
    private CityListIndexView indexView;

    public CityAdapter(List<CityItem> cityItems) {
        this.cityItems = cityItems;
    }

    public List<CityItem> getCityItems() {
        return cityItems;
    }

    public void setCityItems(List<CityItem> cityItems) {
        this.cityItems = cityItems;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setIndexView(CityListIndexView indexView) {
        this.indexView = indexView;
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
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(cityItems.get(position));
                }
                if (indexView != null)
                    indexView.setIndexHighLight(item.airportPinyinShort.substring(0, 1));
            }
        });

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
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemIndexClick(holder.textView.getText().toString());
                }
                if (indexView != null)
                    indexView.setIndexHighLight(holder.textView.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityItems.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(CityItem cityItem);

        public void onItemIndexClick(String index);
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
