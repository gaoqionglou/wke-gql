package com.wke.gql.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author gql
 * @since 18-2-24.
 */

public class BottomBarLayout extends LinearLayout {
    private static final String TAG = "BottomBarLayout";
    private OnTabSelectedCallBack onTabSelectedCallBack;
    private int currentTab = 0;
    private int selectedItemIndex = -1;
    private ViewPager viewPager;
    private boolean smoothScroll = false;

    public BottomBarLayout(Context context) {
        super(context);
    }

    public BottomBarLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BottomBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottomBarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnTabSelectedCallBack(OnTabSelectedCallBack onTabSelectedCallBack) {
        this.onTabSelectedCallBack = onTabSelectedCallBack;
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }

    public boolean isSmoothScroll() {
        return smoothScroll;
    }

    public void setSmoothScroll(boolean smoothScroll) {
        this.smoothScroll = smoothScroll;
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
        ((BottomItem) getChildAt(this.currentTab)).setSelected(true);
        if (viewPager != null) viewPager.setCurrentItem(currentTab);
    }

    public void notifyMsg(int index, String msg) {
        BottomItem item = (BottomItem) getChildAt(index);
        item.showNotificationMsg(msg);
    }

    public void notifyNumber(int index, int number) {
        BottomItem item = (BottomItem) getChildAt(index);
        item.showNotificationNum(number);
    }

    public void notify(int index) {
        BottomItem item = (BottomItem) getChildAt(index);
        item.showNotification();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                float x = ev.getX();
                float y = ev.getY();
                selectedItemIndex = isInChildView(x, y);
                if (selectedItemIndex != -1) {
                    Log.i(TAG, "dispatchTouchEvent: ACTION_DOWN " + selectedItemIndex);
                    if (onTabSelectedCallBack != null)
                        onTabSelectedCallBack.onTabSelected(selectedItemIndex, (BottomItem) getChildAt(selectedItemIndex));
                }
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (selectedItemIndex != -1 && currentTab != selectedItemIndex) {
                    Log.i(TAG, "dispatchTouchEvent: ACTION_UP " + currentTab + " to " + selectedItemIndex);
                    if (onTabSelectedCallBack != null) {
                        onTabSelectedCallBack.onTabSelected(selectedItemIndex, (BottomItem) getChildAt(selectedItemIndex));
                        onTabSelectedCallBack.onTabChanged(currentTab, selectedItemIndex);
                    }
                    ((BottomItem) getChildAt(currentTab)).setSelected(false);
                    ((BottomItem) getChildAt(selectedItemIndex)).setSelected(true);
                    currentTab = selectedItemIndex;
                }
                break;
            default:
                break;
        }
        if (viewPager != null) viewPager.setCurrentItem(currentTab, smoothScroll);
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

    private int isInChildView(float x, float y) {
        int index = -1;
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
                index = i;
                break;
            }
        }
        return index;
    }


    public interface OnTabSelectedCallBack {
        //当某个Tab被选中时
        public void onTabSelected(int tabIndex, BottomItem selectedItem);

        //当tab改变时
        public void onTabChanged(int fromTabIndx, int toTabIndex);
    }
}
