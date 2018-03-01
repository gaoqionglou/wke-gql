package com.wke.gql.citywidget;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author gql
 * @since 18-3-1.
 */

public class CityListView extends RelativeLayout {
    public CityListView(Context context) {
        super(context);
    }

    public CityListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CityListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public CityListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
