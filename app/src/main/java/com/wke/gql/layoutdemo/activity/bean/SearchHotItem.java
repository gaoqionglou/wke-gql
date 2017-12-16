package com.wke.gql.layoutdemo.activity.bean;


import java.util.List;

/**
 * 热门搜索数据 bean
 *
 * @author gql
 * @since 20171212
 */

public class SearchHotItem {

    private HotLabel hotLabel;
    private String hotType;
    private int hotLabelIconResId;
    private List<Content> tags;

    public HotLabel getHotLabel() {
        return hotLabel;
    }

    public void setHotLabel(HotLabel hotLabel) {
        this.hotLabel = hotLabel;
    }

    public String getHotType() {
        return this.hotLabel.getType();
    }

    public void setHotType(String hotType) {
        this.hotType = hotType;
    }

    public int getHotLabelIconResId() {
        return this.hotLabel.getIconResId();
    }

    public void setHotLabelIconResId(int hotLabelIconResId) {
        this.hotLabelIconResId = hotLabelIconResId;
    }

    public List<Content> getTags() {
        return tags;
    }

    public void setTags(List<Content> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "SearchHotItem{" +
                "hotLabel=" + hotLabel +
                ", hotType=" + hotType +
                ", hotLabelIconResId=" + hotLabelIconResId +
                ", tags=" + tags +
                '}';
    }

    //热门搜索类型
    public enum HotLabel {
        CONSULT("advisory", 1),
        SERVICE("service", 1);
        private int iconResId;
        private String type;

        HotLabel(String hotType, int resId) {
            this.type = hotType;
            this.iconResId = resId;
        }

        public String getType() {
            return this.type;
        }

        public int getIconResId() {
            return this.iconResId;
        }
    }

    public class Content {
        public String title;
        public String url;
        public String type;
    }
}

