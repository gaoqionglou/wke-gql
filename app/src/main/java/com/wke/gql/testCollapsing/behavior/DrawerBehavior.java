package com.wke.gql.testCollapsing.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/12/3.
 */

public class DrawerBehavior extends CoordinatorLayout.Behavior<TextView> {
    private static final String TAG = "DrawerBehavior";
    private int mFrameMaxHeight = 100;
    private int mStartY;

    public DrawerBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
        return dependency instanceof Toolbar;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child,
                                          View dependency) {
        float x = dependency.getX();
        float y = dependency.getY();
        Log.i(TAG, "x:" + x + ",y:" + y);
        int width = dependency.getWidth();
        int height = dependency.getHeight();
        Log.i(TAG, "width:" + width + ",height:" + height);
        int top = dependency.getTop();
        int bot = dependency.getBottom();
        int left = dependency.getLeft();
        int right = dependency.getRight();
        Log.i(TAG, "top:" + top + ",bot:" + bot + ",left:" + left + ",right:" + right);
        if (mStartY == 0) {
            mStartY = (int) dependency.getY();
        }    //计算toolbar从开始移动到最后的百分比
        float percent = dependency.getY() / mStartY;    //改变child的坐标(从消失，到可见)
        child.setY(child.getHeight() * (1 - percent) - child.getHeight());
        return true;
    }
}
