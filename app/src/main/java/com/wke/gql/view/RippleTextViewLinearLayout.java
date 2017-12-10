package com.wke.gql.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by gql on 2017/12/10.
 */

public class RippleTextViewLinearLayout extends LinearLayout {
    private static final String TAG = "RippleTextViewLinearLay";
    private float mStartX, mStartY;
    private float mLastX, mLastY;
    private int currentTab = 0, nextTab, beforeTab;
    private OnTabChangedListener onTabChangedListener;

    public RippleTextViewLinearLayout(Context context) {
        super(context);
    }

    public RippleTextViewLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RippleTextViewLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public RippleTextViewLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public OnTabChangedListener getOnTabChangedListener() {
        return onTabChangedListener;
    }

    public void setOnTabChangedListener(OnTabChangedListener onTabChangedListener) {
        this.onTabChangedListener = onTabChangedListener;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        beforeTab = currentTab;
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = ev.getX();
                mLastY = ev.getY();

                RippleTextView index = (RippleTextView) isInChildView(mLastX, mLastY);
                if (index != null) index.startRipple();
                if (currentTab != indexOfChild(index)) {
                    nextTab = indexOfChild(index);
                    if (onTabChangedListener != null)
                        onTabChangedListener.onTabChanged(currentTab, nextTab);
                    Log.i(TAG, "onTabChanged: " + currentTab + "-->" + nextTab);
                    currentTab = nextTab;
                } else {
                    currentTab = indexOfChild(index);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:

                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public int getNextTab() {
        return nextTab;
    }

    /**
     * 点击位置是否在某个子View内,如果在，返回这个View，不在返回null
     *
     * @param x
     * @param y
     * @return
     */
    private View isInChildView(float x, float y) {
        View v = null;
        for (int i = 0; i < getChildCount(); i++) {
            View childView = getChildAt(i);
            int top = childView.getTop();
            int bottom = childView.getBottom();
            int left = childView.getLeft();
            int right = childView.getRight();
            boolean inWidth = x >= left && x <= right;
            boolean inHeight = y >= top && y <= bottom;
            boolean isInChildView = inWidth && inHeight;
            if (isInChildView) {
                v = childView;
                break;
            }
        }
        return v;
    }

    public interface OnTabChangedListener {
        void onTabChanged(int startTab, int endTab);
    }
}

