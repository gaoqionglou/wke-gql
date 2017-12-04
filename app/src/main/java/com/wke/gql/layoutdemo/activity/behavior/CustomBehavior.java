package com.wke.gql.layoutdemo.activity.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wke.gql.R;
import com.wke.gql.view.CircleImageView;

/**
 * Created by gql on 17-12-4.
 */
//自定义Behavior,观察者是CircleImageView，被观察者是AppBarLayout
public class CustomBehavior extends CoordinatorLayout.Behavior<RelativeLayout> {
    private static final String TAG = "CustomBehavior";
    private Context context;
    private View view;
    private CircleImageView civ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppCompatTextView titleTextView;
    private ImageView arrowBackImageView;
    private CircleImageView dogeIcon;
    //appBarLayout的起始Bottom
    private float anchorStartBottom;
    //appBarLayout的最新Bottom
    private float anchorLastBottom;
    //child的起始x,y
    private float childStartX, childStartY;

    public CustomBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        if (dependency instanceof AppBarLayout) {
            appBarLayout = (AppBarLayout) dependency;
            initView(parent);
        }
        return dependency instanceof AppBarLayout;

    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, RelativeLayout child, View dependency) {
        Log.i(TAG, "onDependentViewChanged:appBarLayout.getBottom()" + appBarLayout.getBottom());
        if (anchorStartBottom == 0) {
            anchorStartBottom = appBarLayout.getBottom();
        }
        anchorLastBottom = appBarLayout.getBottom();
        float end = getStatusBarHeight() + getActionBarHeight();
        float diffMax = anchorStartBottom - end;
        float range = (anchorLastBottom - end) / diffMax;
        Log.i(TAG, "onDependentViewChanged: range -- " + range);
//        if(childStartX ==0 ) childStartX = child.getX();
//        if(childStartY ==0 ) childStartY = child.getY();
//        child.setX(childStartX - childStartX * range);
//        child.setY(childStartY - childStartY* range);
        int statusBarHeight = getStatusBarHeight();
        int childHeight = child.getHeight();
        int childWidth = child.getWidth();
        Log.i(TAG, "statusBarHeight,childWidth,childHeight --" + statusBarHeight + "," + childWidth + "," + childHeight);
//        RelativeLayout.LayoutParams layoutParams =  (RelativeLayout.LayoutParams)(child.findViewById(R.id.doge_icon)).getLayoutParams();
//        layoutParams.width = (int)getChangeValue(range,childWidth,getActionBarHeight());
//        layoutParams.height = (int)getChangeValue(range,childHeight,getActionBarHeight());
//        (child.findViewById(R.id.doge_icon)).setLayoutParams(layoutParams);
        return true;
    }

    /**
     * 初始化
     *
     * @param parent
     */
    private void initView(CoordinatorLayout parent) {
        arrowBackImageView = (ImageView) appBarLayout.findViewById(R.id.activity_layout_behaviordemo_arrow_back);
        titleTextView = (AppCompatTextView) appBarLayout.findViewById(R.id.activity_layout_behaviordemo_title);
        dogeIcon = (CircleImageView) parent.findViewById(R.id.doge_icon);
        collapsingToolbarLayout = (CollapsingToolbarLayout) parent.findViewById(R.id.activity_layout_behaviordemo_toolbarLayout);
    }

    float getChangeValue(float range, float start, float end) {
        return end + (start - end) * range;
    }

    /**
     * 获取状态栏高度——方法1
     */
    int getStatusBarHeight() {
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
            Log.i(TAG, "actionBarHeight --" + actionBarHeight);
        }
        return actionBarHeight;
    }
}
