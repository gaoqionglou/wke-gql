package com.wke.gql.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.wke.gql.R;

/**
 * Created by gql on 2017/12/10.
 */

public class Slider extends View {
    private static final String TAG = "Slider";
    private Paint paint;
    private Canvas canvas;
    private int mWidth, mHeight;
    private int current = 0;
    private RippleTextViewLinearLayout linearLayout;
    private int subWidth;
    private boolean isOrigin = true;
    private int backgroundColor;
    private int right;
    private int left;

    public Slider(Context context) {
        super(context);
    }

    public Slider(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Slider(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @TargetApi(21)
    public Slider(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Slider, defStyleAttr, 0);
        backgroundColor = a.getColor(R.styleable.Slider_backgroundColor, Color.BLACK);
        a.recycle();
        init();
    }

    public void attacthTo(RippleTextViewLinearLayout linearLayout) {
        this.linearLayout = linearLayout;

        linearLayout.setOnTabChangedListener(new RippleTextViewLinearLayout.OnTabChangedListener() {
            @Override
            public void onTabChanged(int start, int end) {
                isOrigin = false;
                change(start, end);
                invalidate();
            }
        });
    }

    private void init() {
        paint = new Paint();
        paint.setColor(backgroundColor);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        int childCount = linearLayout.getChildCount();
        subWidth = mWidth / childCount;
        if (isOrigin) {
            canvas.drawRect(current * subWidth, 0, (current + 1) * subWidth, mHeight, paint);
        } else {
            canvas.drawRect(left, 0, right, mHeight, paint);
        }

    }

    public void change(int start, int end) {
        int startRightPosition = (start + 1) * subWidth;
        int endRightPosition = (end + 1) * subWidth;
        int startLeftPosition = start * subWidth;
        int endLeftPosition = end * subWidth;
        Log.i(TAG, "onTabChanged: LeftPosition" + startLeftPosition + "-" + endLeftPosition);
        Log.i(TAG, "onTabChanged: RightPosition" + startRightPosition + "-" + endRightPosition);
        ValueAnimator rightValue = ValueAnimator.ofInt(startRightPosition, endRightPosition);
        rightValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                right = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        rightValue.setInterpolator(new LinearInterpolator());

        ValueAnimator leftValue = ValueAnimator.ofInt(startLeftPosition, endLeftPosition);
        leftValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                left = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        rightValue.setInterpolator(new LinearInterpolator());
        leftValue.setInterpolator(new LinearInterpolator());
        AnimatorSet animatorSet = new AnimatorSet();
        //按顺序执行
        if (start < end) {
            animatorSet.playSequentially(rightValue, leftValue);
        } else {
            animatorSet.playSequentially(leftValue, rightValue);
        }
        animatorSet.setDuration(250);
        animatorSet.start();
    }
}
