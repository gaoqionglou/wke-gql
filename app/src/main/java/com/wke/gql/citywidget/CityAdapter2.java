package com.wke.gql.citywidget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayout;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;
import com.wke.gql.R;
import com.wke.gql.greendao.bean.CityItem;

import java.util.List;

/**
 * Created by gql on 2018/3/11.
 */

public class CityAdapter2 extends RecyclerView.Adapter<CityAdapter2.CityViewHolder> implements StickyRecyclerHeadersAdapter<CityAdapter2.CityIndexViewHolder> {
    private int GPS_HISTORY = 1, LETTER = 2;
    private List<CityData> cityData;
    private OnItemClickListener onItemClickListener;
    private CityListIndexView indexView;
    private List<String> indexData;
    private Context context;

    public CityAdapter2(Context context, List<CityData> cityData) {
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
        int layout = viewType == GPS_HISTORY ? R.layout.item_city2 : R.layout.item_city;
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        CityData data = cityData.get(position);
        List<CityItem> items = data.itemList;
        if (data.index.equalsIgnoreCase("GPS") || data.index.equalsIgnoreCase("历史")) {
            return GPS_HISTORY;
        } else {
            return LETTER;
        }
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        CityData data = cityData.get(position);
        List<CityItem> items = data.itemList;
        if (data.index.equalsIgnoreCase("GPS") || data.index.equalsIgnoreCase("历史")) {
            holder.gridLayout.removeAllViews();
            for (CityItem cityItem : items) {
                AppCompatTextView textView = new AppCompatTextView(context);
                GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f),
                        GridLayout.spec(GridLayout.UNDEFINED, 1f));
                layoutParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
                layoutParams.width = GridLayout.LayoutParams.WRAP_CONTENT;
                //设置一些Margin
                layoutParams.setMargins(8, 8, 8, 8);
                layoutParams.setGravity(Gravity.CENTER);
                textView.setText(cityItem.cityCnName);
                textView.setBackgroundResource(R.drawable.select_city_bg);
                holder.gridLayout.addView(textView, layoutParams);
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
            textView = (AppCompatTextView) itemView.findViewById(R.id.item_city_name);
            linearlayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
            gridLayout = (GridLayout) itemView.findViewById(R.id.gridLayout);
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
