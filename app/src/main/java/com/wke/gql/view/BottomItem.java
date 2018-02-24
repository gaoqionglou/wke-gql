package com.wke.gql.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.wke.gql.R;

/**
 * @author gql
 * @since 18-2-24.
 */

public class BottomItem extends LinearLayout {
    private AppCompatImageView icon, redPoint;
    private AppCompatTextView text, num;
    private int iconDrawable;
    private String textStr;
    private int textSize;
    private int textNormalColor;
    private int textClickedColor;

    public BottomItem(Context context) {
        super(context);
    }

    public BottomItem(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    public BottomItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public BottomItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.BottomItem, 0, defStyleRes);

        iconDrawable = a.getResourceId(R.styleable.BottomItem_item_icon, 0);

        textStr = a.getString(R.styleable.BottomItem_item_text);

        textNormalColor = a.getColor(R.styleable.BottomItem_item_text_normal_color, Color.GRAY);

        textClickedColor = a.getColor(R.styleable.BottomItem_item_text_clicked_color, Color.GRAY);
        a.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_bottom_item, this, true);
        initView();
    }

    private void initView() {
        icon = (AppCompatImageView) findViewById(R.id.bottom_item_icon);
        icon.setImageResource(iconDrawable);
        icon.setClickable(true);
        text = (AppCompatTextView) findViewById(R.id.bottom_item_text);
        text.setTextSize(8);
        text.setTextColor(createColorStateList(textNormalColor, textClickedColor));
        text.setText(textStr);
        text.setClickable(true);
        redPoint = (AppCompatImageView) findViewById(R.id.bottom_red_point);
        redPoint.setBackgroundResource(R.drawable.shape_msg_unread_count_bg);

        num = (AppCompatTextView) findViewById(R.id.bottom_item_num);
        num.setBackgroundResource(R.drawable.shape_msg_unread_count_bg);
    }

    private ColorStateList createColorStateList(int normal, int pressed) {
        int[] colors = new int[]{pressed, normal};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_enabled};
        ColorStateList colorList = new ColorStateList(states, colors);
        return colorList;
    }
}
