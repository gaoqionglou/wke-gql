package com.wke.gql.shop;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.wke.gql.R;

/**
 * @author gql
 * @since 18-2-26.
 */

public class ShopView extends View {
    private static final String TAG = "ShopView";
    private int mWidth, mHeight;
    //矩形按钮的长宽
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
    private int hintTextSize;
    //左右2个圆的半径
    private float radius;
    //左右2个圆的边框宽度
    private float circleBorderWidth;
    //左右圆及其边框的颜色
    private int leftCircleColor, leftCircleBorderColor, rightCircleColor, rightCircleBorderColor;
    //左右2个圆的距离 (不是圆心到圆心,是最右侧到最左侧)
    private float gapBetweenCircle;
    private Paint rectPaint, hintPaint;
    private Paint leftCirclePaint, leftCircleBorderPaint, rightCirclePaint, rightCircleBorderPaint;
    private Path leftPath, rightPath;
    private Rect rect;


    private boolean hintMode = false;

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

        rectWidth = a.getDimension(R.styleable.ShopView_rectWidth, 100);
        rectHeight = a.getDimension(R.styleable.ShopView_rectHeight, 30);
        rectColor = a.getColor(R.styleable.ShopView_rectColor, Color.YELLOW);
        rectCornerRadius = a.getDimension(R.styleable.ShopView_rectCornerRadius, 0);

        hintText = a.getString(R.styleable.ShopView_hintText);
        hintTextColor = a.getColor(R.styleable.ShopView_hintTextColor, Color.BLACK);
        hintTextSize = a.getDimensionPixelSize(R.styleable.ShopView_hintTextSize, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));

        radius = a.getDimension(R.styleable.ShopView_radius, 0);
        circleBorderWidth = a.getDimension(R.styleable.ShopView_circleBorderWidth, 0);
        leftCircleColor = a.getColor(R.styleable.ShopView_leftCircleColor, Color.BLUE);
        leftCircleBorderColor = a.getColor(R.styleable.ShopView_leftCircleBorderColor, Color.BLUE);
        rightCircleColor = a.getColor(R.styleable.ShopView_rightCircleColor, Color.BLUE);
        rightCircleBorderColor = a.getColor(R.styleable.ShopView_rightCircleBorderColor, Color.BLUE);

        gapBetweenCircle = a.getDimension(R.styleable.ShopView_gapBetweenCircle, 40);
        a.recycle();
        init();
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

        leftCirclePaint = new Paint();
        leftCirclePaint.setColor(leftCircleColor);
        leftCirclePaint.setStyle(Paint.Style.FILL);
        leftCircleBorderPaint = new Paint();
        leftCircleBorderPaint.setColor(leftCircleBorderColor);
        leftCircleBorderPaint.setStyle(Paint.Style.STROKE);
        leftCircleBorderPaint.setStrokeWidth(circleBorderWidth);

        rightCirclePaint = new Paint();
        rightCirclePaint.setStyle(Paint.Style.FILL);
        rightCirclePaint.setColor(rightCircleColor);
        rightCircleBorderPaint = new Paint();
        rightCircleBorderPaint.setColor(rightCircleBorderColor);
        rightCircleBorderPaint.setStyle(Paint.Style.STROKE);
        rightCircleBorderPaint.setStrokeWidth(circleBorderWidth);
        leftPath = new Path();
        rightPath = new Path();

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
//        canvas.drawRoundRect(getPaddingLeft(), getPaddingTop(), mWidth - getPaddingRight(), mHeight - getPaddingBottom(), rectCornerRadius, rectCornerRadius, rectPaint);
//        //计算baseline绘制的起点X坐标
//        float baseX = (mWidth / 2 - hintPaint.measureText(hintText) / 2);
//        //计算baseline绘制的起点Y坐标
//        float baseY = mHeight / 2 - (hintPaint.descent() + hintPaint.ascent()) / 2f;
//        canvas.drawText(hintText, baseX, baseY, hintPaint);

        leftPath.addCircle(getPaddingLeft() + radius, getPaddingTop() + radius, radius, Path.Direction.CW);
        canvas.drawPath(leftPath, leftCirclePaint);
        leftPath.reset();
        leftPath.addCircle(getPaddingLeft() + radius, getPaddingTop() + radius, radius, Path.Direction.CW);
        canvas.drawPath(leftPath, leftCircleBorderPaint);


        rightPath.addCircle(getPaddingLeft() + radius * 2 + gapBetweenCircle + radius, getPaddingTop() + radius, radius, Path.Direction.CW);
        canvas.drawPath(rightPath, rightCirclePaint);
        rightPath.reset();
        rightPath.addCircle(getPaddingLeft() + radius * 2 + gapBetweenCircle + radius, getPaddingTop() + radius, radius, Path.Direction.CW);
        canvas.drawPath(rightPath, rightCircleBorderPaint);

    }
}
