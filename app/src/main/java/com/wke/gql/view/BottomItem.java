package com.wke.gql.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
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
    private int textSelectedColor;
    private BottomItemSelectedCallBack callBack;

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
        textSize = a.getDimensionPixelSize(R.styleable.BottomItem_item_text_size, (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_SP, 15, getResources().getDisplayMetrics()));//px
        textNormalColor = a.getColor(R.styleable.BottomItem_item_text_normal_color, Color.GRAY);

        textSelectedColor = a.getColor(R.styleable.BottomItem_item_text_selected_color, Color.GRAY);
        a.recycle();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_bottom_item, this, true);
        initView();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        icon.setSelected(selected);
        text.setSelected(selected);
    }

    public void setBottomItemSelectedCallBack(BottomItemSelectedCallBack callBack) {
        this.callBack = callBack;
    }


    private void initView() {
        icon = (AppCompatImageView) findViewById(R.id.bottom_item_icon);
        icon.setImageResource(iconDrawable);
        icon.setOnClickListener(new BottomItemClickListener(callBack));
        text = (AppCompatTextView) findViewById(R.id.bottom_item_text);
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);//不能以sp为单位，要以px为单位设值
        text.setTextColor(createColorStateList(textSelectedColor, textNormalColor));
        text.setText(textStr);
        text.setOnClickListener(new BottomItemClickListener(callBack));
        redPoint = (AppCompatImageView) findViewById(R.id.bottom_red_point);
        redPoint.setBackgroundResource(R.drawable.shape_msg_unread_count_bg);
        num = (AppCompatTextView) findViewById(R.id.bottom_item_num);
        num.setBackgroundResource(R.drawable.shape_msg_unread_count_bg);
    }

    private ColorStateList createColorStateList(int selectedColor, int unSelectedColor) {
        int[] colors = new int[]{selectedColor, unSelectedColor};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_selected};
        states[1] = new int[]{-android.R.attr.state_selected};
        return new ColorStateList(states, colors);
    }

    public void showNotification() {
        num.setVisibility(GONE);
        if (redPoint.getVisibility() == View.INVISIBLE || redPoint.getVisibility() == View.GONE) {
            redPoint.setVisibility(VISIBLE);
        }
    }

    public void showNotificationNum(int number) {
        redPoint.setVisibility(GONE);
        if (num.getVisibility() == View.INVISIBLE || num.getVisibility() == View.GONE) {
            num.setVisibility(VISIBLE);
            num.setText(number > 99 ? "99+" : String.valueOf(number));
        }
    }

    public void showNotificationMsg(String msg) {
        redPoint.setVisibility(GONE);
        if (num.getVisibility() == View.INVISIBLE || num.getVisibility() == View.GONE) {
            num.setVisibility(VISIBLE);
            num.setText(String.valueOf(msg));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public interface BottomItemSelectedCallBack {
        public void onItemSelected();
    }

    private class BottomItemClickListener implements OnClickListener {
        private BottomItemSelectedCallBack callBack;

        public BottomItemClickListener(BottomItemSelectedCallBack callBack) {
            this.callBack = callBack;
        }

        @Override
        public void onClick(View v) {
            if (callBack != null) {
                callBack.onItemSelected();
            }
        }
    }
}
