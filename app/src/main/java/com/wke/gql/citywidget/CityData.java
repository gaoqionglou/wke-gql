package com.wke.gql.citywidget;

import com.wke.gql.greendao.bean.CityItem;

import java.util.List;

/**
 * @author gql
 * @since 18-3-13.
 */

public class CityData {
    public String index;
    public List<CityItem> itemList;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<CityItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<CityItem> itemList) {
        this.itemList = itemList;
    }
}
