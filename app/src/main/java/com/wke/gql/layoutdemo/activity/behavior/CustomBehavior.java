package com.wke.gql.layoutdemo.activity.behavior;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
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
    float r;
    private RelativeLayout doge_rl;
    private RelativeLayout toolBarSubLayout;
    private Context context;
    private View view;
    private CircleImageView civ;
    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppCompatTextView titleTextView;
    private ImageView arrowBackImageView;
    private CircleImageView dogeIcon, dogeIconInToolBar;
    //appBarLayout的起始Bottom
    private float anchorStartBottom;
    //appBarLayout的最新Bottom
    private float anchorLastBottom;
    //dogeIcon的起始宽 高
    private float childStartWidth, childStartHeight;
    //dogeIcon的起始x,y
    private float childStartX, childStartY;
    //dogeIcon所在布局起始高度
    private int dogeRlStartHeight;
    //dogeIcon起始底部margin
    private int dogeIconStartBottomMargain;

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
        initStartParams();
        anchorLastBottom = appBarLayout.getBottom();
        float end = getStatusBarHeight() + getActionBarHeight();
        float diffMax = anchorStartBottom - end;
        float range = (anchorLastBottom - end) / diffMax;
        CoordinatorLayout.LayoutParams rlParams = (CoordinatorLayout.LayoutParams) doge_rl.getLayoutParams();
        rlParams.height = (int) getChangeHeightValue(range, dogeRlStartHeight, getActionBarHeight());
        doge_rl.setLayoutParams(rlParams);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) dogeIcon.getLayoutParams();
        if (dogeIconStartBottomMargain == 0) dogeIconStartBottomMargain = layoutParams.bottomMargin;
        layoutParams.height = (int) getChangeHeightValue(range, childStartHeight, getActionBarHeight());
        layoutParams.width = (int) getChangeHeightValue(range, childStartWidth, getActionBarHeight());
        layoutParams.bottomMargin = (int) getMargin(range, 0, (int) context.getResources().getDimension(R.dimen.minMargin));
        layoutParams.topMargin = (int) getMargin(range, 0, (int) context.getResources().getDimension(R.dimen.minMargin));
        dogeIcon.setX(getChangeXValue(range, childStartX, toolBarSubLayout.getLeft()));
        dogeIcon.setLayoutParams(layoutParams);
        dogeIcon.setAlpha(hide(range, 0.5f));
        titleTextView.setAlpha(hide(range, 0.5f));
        if (range < 0.5f) {
            dogeIcon.setAlpha(0f);
            dogeIconInToolBar.setAlpha(1f);
        }
        if (range > 0.5) {
            dogeIconInToolBar.setVisibility(View.VISIBLE);
            dogeIconInToolBar.setAlpha(show(range, 0.2f));
        }
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
        doge_rl = (RelativeLayout) parent.findViewById(R.id.doge_rl);
        dogeIcon = (CircleImageView) doge_rl.findViewById(R.id.doge_icon);
        collapsingToolbarLayout = (CollapsingToolbarLayout) parent.findViewById(R.id.activity_layout_behaviordemo_toolbarLayout);
        toolBarSubLayout = (RelativeLayout) parent.findViewById(R.id.sub_layout_toolbar);
        dogeIconInToolBar = (CircleImageView) toolBarSubLayout.findViewById(R.id.toolbar_doge_icon);
    }


    private void initStartParams() {
        if (anchorStartBottom == 0) anchorStartBottom = appBarLayout.getBottom();
        if (childStartX == 0) childStartX = dogeIcon.getX();
        if (childStartY == 0) childStartY = dogeIcon.getY();
        if (childStartHeight == 0) childStartHeight = dogeIcon.getHeight();
        if (childStartWidth == 0) childStartWidth = dogeIcon.getWidth();
        if (dogeRlStartHeight == 0) dogeRlStartHeight = doge_rl.getHeight();
    }

    private float getChangeXValue(float range, float start, float end) {
        return end + (start - end) * range;

    }

    private float getChangeHeightValue(float range, float start, float end) {
        float height = end + (start - end) * range;
        if (height <= getActionBarHeight()) {
            return getActionBarHeight();
        }
        return height;
    }

    /**
     * 获取状态栏高度——方法1
     */
    private int getStatusBarHeight() {
        int statusBarHeight1 = -1;
        //获取status_bar_height资源的ID
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight1 = context.getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight1;
    }

    private int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        int actionBarHeight = 0;
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, context.getResources().getDisplayMetrics());
        }
        return actionBarHeight;
    }


    private float hide(float range, float speedSeed) {
        r = range;
        ValueAnimator value = ValueAnimator.ofFloat(0f, speedSeed);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                r = r - value;
            }
        });
        value.setDuration(100).start();
        if (r > 1f) {
            return 1f;
        } else if (r < 0f) {
            return 0f;
        }
        return r;
    }

    private float show(float range, float speedSeed) {
        r = 1 - range;
        ValueAnimator value = ValueAnimator.ofFloat(0f, speedSeed);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                r = r + value;
            }
        });
        value.setDuration(500).start();
        if (r > 1f) {
            return 1f;
        } else if (r < 0f) {
            return 0f;
        }
        return r;
    }

    private float getMargin(float range, float start, float end) {
        return end + (start - end) * range;
    }

    void speed(float speedSeed) {
        ValueAnimator value = ValueAnimator.ofFloat(0f, speedSeed);
        value.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                animation.getAnimatedValue();
            }
        });
    }
}
