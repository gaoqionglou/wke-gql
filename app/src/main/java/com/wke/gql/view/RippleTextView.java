package com.wke.gql.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;

/**
 * 点击有波浪发散效果的TextView
 */

public class RippleTextView extends AppCompatTextView {
    private int mWidth, mHeight;
    private Paint mRipplePaint;
    private int mMaxRadius;
    private float mRippleRange;//[0f,1f]
    private int px, py;
    private boolean isClicked = false;
    public RippleTextView(Context context) {
        super(context);
        initComponent();
    }

    public RippleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    public RippleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initComponent();
    }

    private void initComponent() {
        mRipplePaint = new Paint();
        mRipplePaint.setColor(Color.GRAY);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mMaxRadius = Math.max(mWidth, mHeight) / 2;
        px = mWidth / 2;
        py = mHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mRipplePaint.setAlpha(255 - (int) (255 * mRippleRange));
        canvas.drawCircle(px, py, mMaxRadius * mRippleRange, mRipplePaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isClicked = true;
        startRipple();
        return super.onTouchEvent(event);
    }

    public void startRipple() {
        ValueAnimator rippleAnimator = ValueAnimator.ofFloat(0f, 1f);
        rippleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRippleRange = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        rippleAnimator.setDuration(500);
        rippleAnimator.setInterpolator(new LinearInterpolator());
        rippleAnimator.start();
    }
}
