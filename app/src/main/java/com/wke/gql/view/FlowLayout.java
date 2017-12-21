package com.wke.gql.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wke.gql.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 流式布局 每个孩子需要高度一致，宽度可以不一样
 * Created by gql on 17-12-14.
 */
public class FlowLayout extends ViewGroup {
    private static final String TAG = "FlowHorizontalLayout";
    private int maxWidth;
    private TreeMap<Integer, Row> rowMap = new TreeMap<>();
    private int horizontal_space, vertical_space;

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FlowLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(21)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FlowLayout);
        horizontal_space = (int) array.getDimension(R.styleable.FlowLayout_flow_horizontal_space, 0);
        vertical_space = (int) array.getDimension(R.styleable.FlowLayout_flow_vertical_space, 0);
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //第一步,测试孩子再测试自己，确定自己需要多少空间
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);//获取容器推荐宽度
        maxWidth = width - getPaddingLeft() - getPaddingRight();
        Log.i(TAG, "maxWidth: " + maxWidth);
        rowMap.clear();
        int sum = 0;
        int rowIndex = 1;
        //按顺序遍历孩子
        for (int i = 0; i < getChildCount(); i++) {
            Row row = null;
            //第一行开始
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);//测量孩子

            if (maxWidth - sum >= child.getMeasuredWidth() + horizontal_space) {
                Log.i(TAG, "sum=" + sum + "," + child.getTag().toString() + "属于" + rowIndex + ",长度=" + (child.getMeasuredWidth() + horizontal_space));
                if (rowMap.get(rowIndex) != null) {
                    row = rowMap.get(rowIndex);
                } else {
                    row = new Row();
                }
                row.views.add(child);
                row.rowIndex = rowIndex;
                rowMap.put(rowIndex, row);
                sum = sum + child.getMeasuredWidth() + horizontal_space;
            } else {
                sum = child.getMeasuredWidth() + horizontal_space;
                rowIndex++;
                Log.i(TAG, child.getTag().toString() + "属于" + rowIndex + ",长度=" + (child.getMeasuredWidth() + horizontal_space));
                row = new Row();
                row.views.clear();
                row.views.add(child);
                rowMap.put(rowIndex, row);
            }
        }
        //打印出来看下分行结果
        Log.i(TAG, "onMeasure: " + rowMap.toString());
        int height = getTotalHeight(rowMap) + vertical_space;/*这是最后一行孩子距离父容器底部之间的间距*/
        //最终确定了容器大小
        setMeasuredDimension(width, height);
    }

    //根据分行结果放置孩子
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.i(TAG, l + "|" + t + "|" + r + "|" + b);
        int left = getPaddingLeft();
        int top = getPaddingTop();
        for (Map.Entry<Integer, Row> entry : rowMap.entrySet()) {
            Row row = entry.getValue();
            for (View v : row.views) {
                v.layout(left, top, left + v.getMeasuredWidth(), top + v.getMeasuredHeight());
                Log.i(TAG, "onLayout: " + " left--" + (left) + "top--" + (top) + "--right:" + (left + v.getMeasuredWidth() + horizontal_space) + "--bottom:" + (top + v.getMeasuredHeight()));
                left = left + v.getMeasuredWidth() + /*这是孩子左右之间的间距*/horizontal_space;
            }
            top = top + getRowMaxHeight(row) + /*这是孩子上下之间的间距*/vertical_space;
            left = getPaddingLeft();
        }
    }

    //获取每一行的最大高度
    private int getRowMaxHeight(Row row) {
        int maxHeight = 0;
        for (View v : row.views) {
            int height = v.getMeasuredHeight();
            if (height > maxHeight) {
                maxHeight = height;
            }
        }

        return maxHeight + vertical_space;
    }

    private int getTotalHeight(Map<Integer, Row> rowMap) {
        int sum = 0;
        for (Map.Entry<Integer, Row> entry : rowMap.entrySet()) {
            Row row = entry.getValue();
            sum = sum + getRowMaxHeight(row);
        }
        return sum;
    }

    //获取从第一行到第n行的高度
    private int getRowHeightSum(int rowCount) {
        int height = 0;
        if (rowCount == 0) return getPaddingTop();
        for (int i = 1; i <= rowCount; i++) {
            Row row = rowMap.get(i);
            height = height + getRowMaxHeight(row) + vertical_space;
        }
        return height;
    }


    private int getRowMaxTopMargin(Row row) {
        int topMaxMargin = 0;
        for (View v : row.views) {
            MarginLayoutParams params = (MarginLayoutParams) v.getLayoutParams();
            if (params.topMargin > topMaxMargin) topMaxMargin = params.topMargin;
        }
        return topMaxMargin;
    }

    //Row来定义一行来放置多少个View
    private class Row {
        public List<View> views = new ArrayList<>();
        public int rowIndex;

        @Override
        public String toString() {
            String out = rowIndex + "-";
            for (View v : views) {
                out = out + v.getTag() + "-";
            }
            return out;
        }
    }


}
