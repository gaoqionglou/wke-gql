package com.wke.gql.citywidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.wke.gql.R;
import com.wke.gql.utils.UtilTool;

import java.util.List;

/**
 * @author gql
 * @since 18-3-1.
 */

public class CityListIndexView extends LinearLayout {
    private List<String> indexContents;
    private int indexTextColor, indexTextSize, indexTextBackground, indexBackgroud;

    public CityListIndexView(Context context) {
        super(context);
    }

    public CityListIndexView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CityListIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    @TargetApi(21)
    public CityListIndexView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CityListIndexView, defStyleAttr, defStyleRes);
        indexTextColor = a.getColor(R.styleable.CityListIndexView_indexTextColor, Color.BLACK);
        indexTextSize = a.getDimensionPixelSize(R.styleable.CityListIndexView_indexTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 8, getResources().getDisplayMetrics()));
        indexTextBackground = a.getResourceId(R.styleable.CityListIndexView_indexTextBackground, R.drawable.select_city_bg);
        indexBackgroud = a.getResourceId(R.styleable.CityListIndexView_indexBackground, R.drawable.search_tag_bg);
        a.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_citylistindex, this, true);
        initView();
    }

    private void initView() {
        LinearLayout layout = (LinearLayout) this.findViewById(R.id.indexList);
        layout.setBackgroundResource(indexBackgroud);
        if (indexContents != null && !indexContents.isEmpty()) {
            for (int i = 0; i < indexContents.size(); i++) {
                AppCompatTextView textView = new AppCompatTextView(getContext());
                LinearLayout.LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                params.setMargins(0, UtilTool.dip2px(5), 0, UtilTool.dip2px(5));
                params.weight = 1;
                textView.setGravity(Gravity.CENTER);
                textView.setText(indexContents.get(i));
                textView.setTextColor(indexTextColor);
                textView.setBackgroundResource(indexTextBackground);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, indexTextSize);
                layout.addView(textView, params);
            }
        }
    }


    public List<String> getIndexContents() {
        return indexContents;
    }

    public void setIndexContents(List<String> indexContents) {
        this.indexContents = indexContents;
    }

    public int getIndexTextColor() {
        return indexTextColor;
    }

    public void setIndexTextColor(int indexTextColor) {
        this.indexTextColor = indexTextColor;
    }

    public int getIndexTextSize() {
        return indexTextSize;
    }

    public void setIndexTextSize(int indexTextSize) {
        this.indexTextSize = indexTextSize;
    }

    public int getIndexTextBackground() {
        return indexTextBackground;
    }

    public void setIndexTextBackground(int indexTextBackground) {
        this.indexTextBackground = indexTextBackground;
    }

    public int getIndexBackgroud() {
        return indexBackgroud;
    }

    public void setIndexBackgroud(int indexBackgroud) {
        this.indexBackgroud = indexBackgroud;
    }

}
