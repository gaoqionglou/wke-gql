package com.wke.gql.citywidget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.wke.gql.R;
import com.wke.gql.greendao.bean.CityItem;

import java.util.List;

/**
 * Created by gql on 2018/3/11.
 */

public class CityUpgradedAdapter extends RecyclerView.Adapter<CityUpgradedAdapter.CityViewHolder> implements StickyRecyclerHeadersAdapter<CityUpgradedAdapter.CityIndexViewHolder> {
    private int GPS_HISTORY_HOT = 1, LETTER = 2;
    private List<CityData> cityData;
    private OnItemClickListener onItemClickListener;
    private CityListIndexView indexView;
    private List<String> indexData;
    private Context context;

    public CityUpgradedAdapter(Context context, List<CityData> cityData) {
        this.context = context;
        this.cityData = cityData;
    }

    public List<String> getIndexData() {
        return indexData;
    }

    public void setIndexData(List<String> indexData) {
        this.indexData = indexData;
    }

    public List<CityData> getCityData() {
        return cityData;
    }

    public void setCityData(List<CityData> cityData) {
        this.cityData = cityData;
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
        int layout = viewType == GPS_HISTORY_HOT ? R.layout.item_city2 : R.layout.item_city;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        CityData data = cityData.get(position);
        List<CityItem> items = data.itemList;
        if ("GPS/历史".equalsIgnoreCase(data.index) || "热门".equalsIgnoreCase(data.index)) {
            return GPS_HISTORY_HOT;
        } else {
            return LETTER;
        }
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityData data = cityData.get(position);
        List<CityItem> items = data.itemList;
        if ("GPS/历史".equalsIgnoreCase(data.index) || "热门".equalsIgnoreCase(data.index)) {
            holder.gridLayout.removeAllViews();
            for (int i = 0; i < items.size(); i++) {
                CityItem cityItem = items.get(i);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(i / 3, 1f),
                        GridLayout.spec(i % 3, 1f));
                layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.width = 0;
                //设置一些Margin
                layoutParams.setMargins(30, 10, 30, 10);
                View view = LayoutInflater.from(context).inflate(R.layout.item_city_name, null);
                ImageView imageView = (ImageView) view.findViewById(R.id.iv_gpsIcon);
                imageView.setVisibility(cityItem.isGPS ? View.VISIBLE : View.GONE);
                TextView textView = (TextView) view.findViewById(R.id.tv_cityName);
                textView.setText(cityItem.cityCnName);
                holder.gridLayout.addView(view, layoutParams);
            }
        } else {
            if ("1".equalsIgnoreCase(items.get(0).isDomestic)) {
                holder.textView.setText(items.get(0).cityCnName);
            } else {
                holder.textView.setText(items.get(0).cityCnName + "(" + items.get(0).getCityCode() + "/" + items.get(0).getCountryCnName() + ")");
            }
        }

    }

    @Override
    public long getHeaderId(int position) {
        return cityData.get(position).index.charAt(0);
    }

    @Override
    public CityIndexViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_city_index, parent, false);
        return new CityIndexViewHolder(view);
    }

    @Override
    public void onBindHeaderViewHolder(CityIndexViewHolder holder, int position) {
        holder.textView.setText(cityData.get(position).index);
        holder.textView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemIndexClick(holder.textView.getText().toString());
            }
            if (indexView != null)
                indexView.setIndexHighLight(holder.textView.getText().toString());
        });
    }

    @Override
    public int getItemCount() {
        return cityData.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(CityItem cityItem);

        public void onItemIndexClick(String index);
    }

    public class CityViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textView;
        public LinearLayout linearlayout;
        public GridLayout gridLayout;

        public CityViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_city_name);
            linearlayout = itemView.findViewById(R.id.linearlayout);
            gridLayout = itemView.findViewById(R.id.gridLayout);
        }
    }

    public class CityIndexViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView textView;

        public CityIndexViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_city_index);
        }
    }
}
