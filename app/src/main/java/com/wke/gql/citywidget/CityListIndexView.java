package com.wke.gql.citywidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.wke.gql.R;

import java.util.Arrays;
import java.util.List;

/**
 * @author gql
 * @since 18-3-1.
 */

public class CityListIndexView extends LinearLayout {
    private static final String TAG = "CityListIndexView";
    private List<String> indexContents = Arrays.asList("GPS", "热门", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
    private int indexTextColor, indexTextSize, indexTextBackground, indexBackgroud;
    private int indexMarginLeft, indexMarginRight, indexMarginTop, indexMarginBottom;
    private int indexTextWidth, indexTextHeight;
    private int indexTextPaddingLeft, indexTextPaddingRight, indexTextPaddingTop, indexTextPaddingBottom;

    private float mLastX, mLastY;
    private OnIndexTextClickCallBack onIndexTextClickCallBack;

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
        indexMarginLeft = a.getDimensionPixelSize(R.styleable.CityListIndexView_indexMarginLeft, 0);
        indexMarginRight = a.getDimensionPixelSize(R.styleable.CityListIndexView_indexMarginRight, 0);
        indexMarginTop = a.getDimensionPixelSize(R.styleable.CityListIndexView_indexMarginTop, 0);
        indexMarginBottom = a.getDimensionPixelSize(R.styleable.CityListIndexView_indexMarginBottom, 0);
        indexTextWidth = a.getDimensionPixelOffset(R.styleable.CityListIndexView_indexTextWidth, 0);
        indexTextHeight = a.getDimensionPixelOffset(R.styleable.CityListIndexView_indexTextHeight, 0);
        indexTextPaddingLeft = a.getDimensionPixelOffset(R.styleable.CityListIndexView_indexTextPaddingLeft, 0);
        indexTextPaddingRight = a.getDimensionPixelOffset(R.styleable.CityListIndexView_indexTextPaddingRight, 0);
        indexTextPaddingBottom = a.getDimensionPixelOffset(R.styleable.CityListIndexView_indexTextPaddingBottom, 0);
        indexTextPaddingTop = a.getDimensionPixelOffset(R.styleable.CityListIndexView_indexTextPaddingTop, 0);
        a.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_citylistindex, this, true);
        initView();
        setClickable(true);//设置为true 为了后续能接收move up事件
    }

    private void initView() {
        LinearLayout layout = (LinearLayout) this.findViewById(R.id.indexList);
        layout.setGravity(Gravity.CENTER);
        layout.setBackgroundResource(indexBackgroud);
        indexTextHeight = indexTextHeight == 0 ? LayoutParams.WRAP_CONTENT : indexTextHeight;
        indexTextWidth = indexTextWidth == 0 ? LayoutParams.WRAP_CONTENT : indexTextWidth;
        if (indexContents != null && !indexContents.isEmpty()) {
            for (int i = 0; i < indexContents.size(); i++) {
                AppCompatTextView textView = new AppCompatTextView(getContext());
                LinearLayout.LayoutParams params = new LayoutParams(indexTextWidth, indexTextHeight);
                params.setMargins(indexMarginLeft, indexMarginTop, indexMarginRight, indexMarginBottom);
                params.weight = 1;
                textView.setPadding(indexTextPaddingLeft, indexTextPaddingTop, indexTextPaddingRight, indexTextPaddingBottom);
                textView.setGravity(Gravity.CENTER);
                textView.setText(indexContents.get(i));
                textView.setTag(indexContents.get(i));
                textView.setTextColor(indexTextColor);
                textView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onIndexTextClickCallBack != null) {
                            onIndexTextClickCallBack.onClick(v);
                        }
                    }
                });
                textView.setOnTouchListener(new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent ev) {
                        if (onIndexTextClickCallBack != null) {
                            onIndexTextClickCallBack.onTouch(v, ev);
                        }
                        return false;
                    }
                });
                textView.setClickable(true);
                textView.setBackgroundResource(indexTextBackground);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, indexTextSize);
                layout.addView(textView, params);
            }
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "dispatchTouchEvent: ACTION_DOWN " + mLastX + "," + mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "dispatchTouchEvent: ACTION_MOVE " + mLastX + "," + mLastY);
                break;
            case MotionEvent.ACTION_UP:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "dispatchTouchEvent: ACTION_UP " + mLastX + "," + mLastY);
                break;
            default:
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "onInterceptHoverEvent: ACTION_DOWN " + mLastX + "," + mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "onInterceptHoverEvent: ACTION_MOVE " + mLastX + "," + mLastY);
                break;
            case MotionEvent.ACTION_UP:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "onInterceptHoverEvent: ACTION_UP " + mLastX + "," + mLastY);
                break;
            default:
                break;
        }
        return super.onInterceptHoverEvent(ev);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "onTouchEvent: ACTION_DOWN " + mLastX + "," + mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "onTouchEvent: ACTION_MOVE " + mLastX + "," + mLastY);
                break;
            case MotionEvent.ACTION_UP:
                mLastX = ev.getX();
                mLastY = ev.getY();
                Log.i(TAG, "onTouchEvent: ACTION_UP " + mLastX + "," + mLastY);
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);

    }

    public List<String> getIndexContents() {
        return indexContents;
    }

    public void setIndexContents(List<String> indexContents) {
        this.indexContents = indexContents;
        initView();
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


    public interface OnIndexTextClickCallBack {
        public void onTouch(View v, MotionEvent ev);

        public void onClick(View v);
    }
}
