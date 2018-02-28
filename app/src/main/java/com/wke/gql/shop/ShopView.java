package com.wke.gql.shop;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.wke.gql.R;

/**
 * @author gql
 * @since 18-2-26.
 */

public class ShopView extends View {
    public static final int STATUS_START = 0;
    public static final int STATUS_END = 1;
    private static final String TAG = "ShopView";
    float x = 0, y = 0;
    private int mWidth, mHeight;
    //矩形按钮的长宽
    @Deprecated
    private float rectWidth, rectHeight;
    //矩形圆角
    private float rectCornerRadius;
    //矩形颜色
    private int rectColor;
    //矩形按钮文字
    private String hintText;
    //文字颜色
    private int hintTextColor;
    //文字大小
    private int hintTextSize, numTextSize;
    //左右2个圆的半径
    private float radius;
    //左右2个圆的边框宽度
    private float circleBorderWidth;
    //左右圆及其边框的颜色
    private int leftCircleColor, leftCircleBorderColor, leftMinusColor, rightCircleColor, rightCircleBorderColor, rightCircleUnableColor, rightAddColor, numColor;
    //左右2个圆的距离 (不是圆心到圆心,是最右侧到最左侧)
    private float gapBetweenCircle;
    private Paint rectPaint, hintPaint;
    private Paint leftCirclePaint, leftCircleBorderPaint, leftMinusPaint, rightCirclePaint, rightCircleBorderPaint, rightAddPaint, numPaint;
    private Path leftPath, rightPath;
    private boolean leftCircleBorderVisibility = false, rightCircleBorderVisibility = false;
    private Region rectRegion, delRegion, addRegion, region;
    private boolean hintMode = false;
    private int num = 0;
    private int maxNum = 0;

    private float rectSeed = 0;//[0,1]
    private float degreeSeed = 0;//[0,1]
    private float gapSeed = 0;//[0,1]
    private float alphaSeed = 0;//[0,1]
    private float signPadding = 10;
    private int animationStatus = STATUS_END;

    private OnButtonClickCallBack onButtonClickCallBack;

    public ShopView(Context context) {
        super(context);
    }

    public ShopView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public ShopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ShopView, defStyleAttr, defStyleRes);

        rectWidth = a.getDimensionPixelSize(R.styleable.ShopView_rectWidth, 100);
        rectHeight = a.getDimensionPixelSize(R.styleable.ShopView_rectHeight, 30);
        rectColor = a.getColor(R.styleable.ShopView_rectColor, Color.YELLOW);
        rectCornerRadius = a.getDimensionPixelSize(R.styleable.ShopView_rectCornerRadius, 0);

        hintText = a.getString(R.styleable.ShopView_hintText);
        hintTextColor = a.getColor(R.styleable.ShopView_hintTextColor, Color.BLACK);
        hintTextSize = a.getDimensionPixelSize(R.styleable.ShopView_hintTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));

        radius = a.getDimensionPixelSize(R.styleable.ShopView_radius, 0);
        circleBorderWidth = a.getDimensionPixelSize(R.styleable.ShopView_circleBorderWidth, 0);
        leftCircleColor = a.getColor(R.styleable.ShopView_leftCircleColor, Color.BLUE);
        leftCircleBorderColor = a.getColor(R.styleable.ShopView_leftCircleBorderColor, Color.BLUE);
        leftMinusColor = a.getColor(R.styleable.ShopView_leftMinusColor, Color.DKGRAY);
        leftCircleBorderVisibility = a.getBoolean(R.styleable.ShopView_leftCircleBorderVisibility, false);
        rightCircleColor = a.getColor(R.styleable.ShopView_rightCircleColor, Color.BLUE);
        rightCircleBorderColor = a.getColor(R.styleable.ShopView_rightCircleBorderColor, Color.BLUE);
        rightCircleUnableColor = a.getColor(R.styleable.ShopView_rightCircleUnableColor, Color.DKGRAY);
        rightAddColor = a.getColor(R.styleable.ShopView_rightAddColor, Color.DKGRAY);
        rightCircleBorderVisibility = a.getBoolean(R.styleable.ShopView_rightCircleBorderVisibility, false);
        gapBetweenCircle = a.getDimensionPixelSize(R.styleable.ShopView_gapBetweenCircle, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics()));
        num = a.getInteger(R.styleable.ShopView_num, 0);
        maxNum = a.getInteger(R.styleable.ShopView_maxNum, 99);
        numColor = a.getColor(R.styleable.ShopView_numTextColor, Color.BLACK);
        numTextSize = a.getDimensionPixelSize(R.styleable.ShopView_numTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));
        signPadding = a.getDimensionPixelSize(R.styleable.ShopView_signPadding, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()));
        a.recycle();
        init();
        setClickable(true);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public OnButtonClickCallBack getOnButtonClickCallBack() {
        return onButtonClickCallBack;
    }

    public void setOnButtonClickCallBack(OnButtonClickCallBack onButtonClickCallBack) {
        this.onButtonClickCallBack = onButtonClickCallBack;
    }

    private void init() {
        initPaint();
    }

    private void initPaint() {
        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setColor(rectColor);


        hintPaint = new Paint();
        hintPaint.setColor(hintTextColor);
        hintPaint.setTextSize(hintTextSize);

        numPaint = new Paint();
        numPaint.setColor(numColor);
        numPaint.setTextSize(numTextSize);


        leftCirclePaint = new Paint();
        leftCirclePaint.setColor(leftCircleColor);
        leftCirclePaint.setStyle(Paint.Style.FILL);
        leftCircleBorderPaint = new Paint();
        leftCircleBorderPaint.setColor(leftCircleBorderColor);
        leftCircleBorderPaint.setStyle(Paint.Style.STROKE);
        leftCircleBorderPaint.setStrokeWidth(circleBorderWidth);

        leftMinusPaint = new Paint();
        leftMinusPaint.setColor(leftMinusColor);
        leftMinusPaint.setStyle(Paint.Style.FILL);
        leftMinusPaint.setStrokeWidth(circleBorderWidth);
        rightCirclePaint = new Paint();
        rightCirclePaint.setStyle(Paint.Style.FILL);
        rightCirclePaint.setColor(rightCircleColor);
        rightCircleBorderPaint = new Paint();
        rightCircleBorderPaint.setColor(rightCircleBorderColor);
        rightCircleBorderPaint.setStyle(Paint.Style.STROKE);
        rightCircleBorderPaint.setStrokeWidth(circleBorderWidth);
        rightAddPaint = new Paint();
        rightAddPaint.setColor(rightAddColor);
        rightAddPaint.setStyle(Paint.Style.FILL);
        rightAddPaint.setStrokeWidth(circleBorderWidth);
        leftPath = new Path();
        rightPath = new Path();

        region = new Region();
        delRegion = new Region();
        addRegion = new Region();
        rectRegion = new Region();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = 0, height = 0;
        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (wMode) {
            case MeasureSpec.EXACTLY://match_parent
                width = wSize;
                gapBetweenCircle = width - getPaddingLeft() - radius * 2 - getPaddingRight() - radius * 2;
                break;
            case MeasureSpec.AT_MOST://wrap_content.xxxdp
                float sumWidth = getPaddingLeft() + radius * 2 + gapBetweenCircle + radius * 2 + getPaddingRight();
                width = (int) sumWidth;
                break;
            case MeasureSpec.UNSPECIFIED://自定默认大小
                break;
            default:
                break;
        }
        switch (hMode) {
            case MeasureSpec.EXACTLY://match_parent
                height = hSize;
                break;
            case MeasureSpec.AT_MOST://wrap_content
                float sumHeight = getPaddingTop() + radius * 2 + getPaddingBottom();
                height = (int) sumHeight;
                break;
            case MeasureSpec.UNSPECIFIED://自定默认大小
                break;
            default:
                break;
        }
        mWidth = width;
        mHeight = height;
        setMeasuredDimension(width, height);
        rectSeed = 0;
        degreeSeed = 0;
        alphaSeed = 0;
        gapSeed = 0;
        Log.i(TAG, "onMeasure: " + toString());
    }

    @Override
    public String toString() {
        return "ShopView{" +
                "mWidth=" + mWidth +
                ", mHeight=" + mHeight +
                ", rectWidth=" + rectWidth +
                ", rectHeight=" + rectHeight +
                ", radius=" + radius +
                ", gapBetweenCircle=" + gapBetweenCircle +
                '}';
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (hintMode) {
            float right = mWidth - getPaddingRight();
            float left = getPaddingLeft();
            float k = right - left;
            float newLeft = k * rectSeed + left;
            canvas.drawRoundRect(newLeft, getPaddingTop(), mWidth - getPaddingRight(), mHeight - getPaddingBottom(), rectCornerRadius, rectCornerRadius, rectPaint);
            if (rectSeed == 1) {
                rectRegion.set(getPaddingLeft(), getPaddingTop(), mWidth - getPaddingRight(), mHeight - getPaddingBottom());
            }
            //计算baseline绘制的起点X坐标
            float baseX = (mWidth / 2 - hintPaint.measureText(hintText) / 2);
            //计算baseline绘制的起点Y坐标
            hintPaint.setAlpha(255 - (int) (255 * rectSeed));
            float baseY = mHeight / 2 - (hintPaint.descent() + hintPaint.ascent()) / 2f;
            canvas.drawText(hintText, baseX, baseY, hintPaint);
        } else {
            //确定左圆的点击范围 region
            leftPath.addCircle(getPaddingLeft() + radius, getPaddingTop() + radius, radius, Path.Direction.CW);
            region.set(getPaddingLeft(), getPaddingTop(), (int) (getPaddingLeft() + radius * 2), (int) (getPaddingTop() + radius * 2));
            delRegion.setPath(leftPath, region);

            canvas.save();
            //平移至圆心坐标，坐标轴原点在圆心
            float translateX = (gapBetweenCircle + radius * 2f) * gapSeed;
            canvas.translate(getPaddingLeft() + radius + translateX, mHeight / 2);
            leftPath.reset();
            leftPath.addCircle(0, 0, radius, Path.Direction.CW);
            canvas.drawPath(leftPath, leftCirclePaint);
            if (leftCircleBorderVisibility) {
                leftPath.reset();
                leftPath.addCircle(0, 0, radius, Path.Direction.CW);
                canvas.drawPath(leftPath, leftCircleBorderPaint);
            }
            canvas.rotate(360 * degreeSeed);
            canvas.drawLine(-radius + signPadding, 0, radius - signPadding, 0, leftMinusPaint);
            canvas.restore();


            canvas.save();
            //绘制中间的数字 0开始
            //计算baseline绘制的起点Y坐标
            float translateTextX = (gapBetweenCircle / 2 + radius) * gapSeed;
            canvas.translate(mWidth / 2 + translateTextX, mHeight / 2);
            canvas.rotate(360 * degreeSeed);
            float baseX = -numPaint.measureText(String.valueOf(num)) / 2f;
            float baseY = (Math.abs(numPaint.ascent()) + numPaint.descent()) / 2f - numPaint.descent();
            numPaint.setAlpha(255 - (int) (255 * alphaSeed));
            canvas.drawText(String.valueOf(num), baseX, baseY, numPaint);
            canvas.restore();

            //绘制右圆
            rightPath.addCircle(getPaddingLeft() + radius * 2 + gapBetweenCircle + radius, mHeight / 2, radius, Path.Direction.CW);
            region.set((int) (getPaddingLeft() + radius * 2 + gapBetweenCircle), getPaddingTop(), (int) (getPaddingLeft() + radius * 2 + gapBetweenCircle + radius * 2), (int) (getPaddingTop() + radius * 2));
            addRegion.setPath(rightPath, region);
            if (num >= maxNum) {
                num = maxNum;
                rightCirclePaint.setColor(rightCircleUnableColor);
            } else {
                rightCirclePaint.setColor(rightCircleColor);
            }
            canvas.drawPath(rightPath, rightCirclePaint);
            if (rightCircleBorderVisibility) {
                rightPath.reset();
                //绘制左圆边框
                rightPath.addCircle(getPaddingLeft() + radius * 2 + gapBetweenCircle + radius, mHeight / 2, radius, Path.Direction.CW);
                canvas.drawPath(rightPath, rightCircleBorderPaint);
            }
            //绘制右圆 '+'
            canvas.drawLine(getPaddingLeft() + radius * 2 + gapBetweenCircle + signPadding, mHeight / 2, getPaddingLeft() + radius * 2 + gapBetweenCircle + radius * 2 - signPadding, mHeight / 2, rightAddPaint);
            canvas.drawLine(getPaddingLeft() + radius * 2 + gapBetweenCircle + radius, mHeight / 2 - radius + signPadding, getPaddingLeft() + radius * 2 + gapBetweenCircle + radius, mHeight / 2 + radius - signPadding, rightAddPaint);

        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (addRegion.contains((int) x, (int) y)) {
                    Log.i(TAG, "onTouchEvent: " + "点击‘+’号" + num);
                    if (num >= 0 && num + 1 <= maxNum) {
                        num = num + 1;
                        if (onButtonClickCallBack != null) {
                            onButtonClickCallBack.onAdd(num);
                        }
                        invalidate();
                        break;
                    }
                }
                if (delRegion.contains((int) x, (int) y)) {
                    Log.i(TAG, "onTouchEvent: " + " 点击‘-’号 " + num);
                    if (num - 1 >= 0) {
                        num = num - 1;
                        if (onButtonClickCallBack != null) {
                            onButtonClickCallBack.onDel(num);
                        }
                        invalidate();
                    }
                    if (num == 0 && !hintMode && animationStatus == STATUS_END) {
                        //执行右圆的平移的动画
                        if (onButtonClickCallBack != null) {
                            onButtonClickCallBack.onHideButton();
                        }
                        minus();
                    }
                }
                if (hintMode && rectRegion.contains((int) x, (int) y) && animationStatus == STATUS_END) {
                    Log.i(TAG, "onTouchEvent: " + "rectRegion " + num);
                    if (onButtonClickCallBack != null) {
                        onButtonClickCallBack.onShowButton();
                    }
                    add();
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * [减号+数字]旋转缩进动画开启,接着进行矩形弹出动画
     */
    private void minus() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(minusInAnimator(), rectOutAnimator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationStatus = STATUS_END;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animationStatus = STATUS_START;
            }
        });
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }


    private void add() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(rectInAnimator(), minusOutAnimator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                animationStatus = STATUS_END;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                animationStatus = STATUS_START;
            }
        });
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }

    /**
     * 减号缩进动画
     */
    private void startMinusIntAnimator() {
        ValueAnimator animator = minusInAnimator();
        animator.setDuration(500);
        animator.start();
    }

    /**
     * 减号缩进动画
     */
    private ValueAnimator minusInAnimator() {
        Log.i(TAG, "减号缩进动画: ");
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                gapSeed = degreeSeed = alphaSeed = (float) animation.getAnimatedValue();
                invalidate();
                if (gapSeed == 1) {
                    hintMode = true;
                }
            }
        });
        return animator;
    }

    /**
     * 减号弹出动画
     */
    private void startMinusOutAnimator() {
        ValueAnimator animator = minusOutAnimator();
        animator.setDuration(500);
        animator.start();
    }

    private ValueAnimator minusOutAnimator() {
        Log.i(TAG, "减号弹出动画: ");
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                gapSeed = (float) animation.getAnimatedValue();
                degreeSeed = (float) animation.getAnimatedValue();
                alphaSeed = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        return animator;
    }

    /**
     * 矩形弹出
     */
    private void startRectOutAnimator() {
        ValueAnimator animator = rectOutAnimator();
        animator.setDuration(500);
        animator.start();
    }

    private ValueAnimator rectOutAnimator() {
        Log.i(TAG, "矩形弹出: ");
        ValueAnimator animator = ValueAnimator.ofFloat(1f, 0f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rectSeed = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        return animator;
    }

    /**
     * 矩形缩进
     */
    private void startRectInAnimator() {
        ValueAnimator animator = rectInAnimator();
        animator.setDuration(500);
        animator.start();
    }

    private ValueAnimator rectInAnimator() {
        Log.i(TAG, "矩形缩进: ");
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                rectSeed = (float) animation.getAnimatedValue();
                invalidate();
                if (rectSeed == 1) {
                    hintMode = false;
                }
            }
        });
        return animator;
    }


    public interface OnButtonClickCallBack {
        void onDel(int num);

        void onAdd(int num);

        void onShowButton();

        void onHideButton();
    }
}
