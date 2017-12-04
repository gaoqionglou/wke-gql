package com.wke.gql.testCollapsing.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wke.gql.R;

public class CollapsingProfileHeaderView extends LinearLayout {

    private int profileDrawable;
    private String profileName;
    private String subtitle;
    private String misc;
    private int miscIcon;
    private int profileNameTextSize;
    private int profileSubtitleTextSize;
    private int profileMiscTextSize;

    private ImageView profileImage;
    private TextView profileNameTextView;
    private TextView subtitleTextView;
    private TextView miscTextView;

    public CollapsingProfileHeaderView(Context context) {
        super(context);
    }

    public CollapsingProfileHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CollapsingProfileHeaderView,
                0, 0);

        try {
            profileDrawable = a.getResourceId(R.styleable.CollapsingProfileHeaderView_profileImage, 0);
            profileName = a.getString(R.styleable.CollapsingProfileHeaderView_profileName);
            subtitle = a.getString(R.styleable.CollapsingProfileHeaderView_profileSubtitle);
            misc = a.getString(R.styleable.CollapsingProfileHeaderView_profileMisc);
            miscIcon = a.getResourceId(R.styleable.CollapsingProfileHeaderView_profileMiscIcon, 0);
            profileNameTextSize = a.getResourceId(R.styleable.CollapsingProfileHeaderView_profileNameTextSizeSp, 20);
            profileSubtitleTextSize = a.getResourceId(R.styleable.CollapsingProfileHeaderView_profileSubtitleTextSizeSp, 12);
            profileMiscTextSize = a.getResourceId(R.styleable.CollapsingProfileHeaderView_profileMiscTextSizeSp, 15);
        } finally {
            a.recycle();
        }

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        inflater.inflate(R.layout.view_collapsing_profile_header, this, true);

        loadViews();
        applyAttributes();
    }

    private void loadViews() {
        profileImage = (ImageView) this.findViewById(R.id.profileImage);
        profileNameTextView = (TextView) this.findViewById(R.id.profileName);
        subtitleTextView = (TextView) this.findViewById(R.id.profileSubtitle);
        miscTextView = (TextView) this.findViewById(R.id.profileMisc);
    }

    private void applyAttributes() {
        profileImage.setImageResource(profileDrawable);
        profileNameTextView.setText(profileName);
        profileNameTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, profileNameTextSize);
        subtitleTextView.setText(subtitle);
        subtitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, profileSubtitleTextSize);
        miscTextView.setText(misc);
        miscTextView.setCompoundDrawablesWithIntrinsicBounds(miscIcon, 0, 0, 0);
        miscTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, profileMiscTextSize);
    }

    public int getProfileDrawable() {
        return profileDrawable;
    }

    public void setProfileDrawable(int profileDrawable) {
        this.profileDrawable = profileDrawable;
        profileImage.setImageResource(profileDrawable);
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
        profileNameTextView.setText(profileName);
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
        subtitleTextView.setText(subtitle);
    }

    public String getMisc() {
        return misc;
    }

    public void setMisc(String misc) {
        this.misc = misc;
        miscTextView.setText(misc);
    }

    public int getMiscIcon() {
        return miscIcon;
    }

    public void setMiscIcon(int miscIcon) {
        this.miscIcon = miscIcon;
        miscTextView.setCompoundDrawablesWithIntrinsicBounds(miscIcon, 0, 0, 0);
    }

    public int getProfileNameTextSize() {
        return profileNameTextSize;
    }

    public void setProfileNameTextSize(int profileNameTextSize) {
        this.profileNameTextSize = profileNameTextSize;
    }

    public int getProfileSubtitleTextSize() {
        return profileSubtitleTextSize;
    }

    public void setProfileSubtitleTextSize(int profileSubtitleTextSize) {
        this.profileSubtitleTextSize = profileSubtitleTextSize;
    }

    public int getProfileMiscTextSize() {
        return profileMiscTextSize;
    }

    public void setProfileMiscTextSize(int profileMiscTextSize) {
        this.profileMiscTextSize = profileMiscTextSize;
    }
}
