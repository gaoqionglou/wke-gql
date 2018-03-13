package com.wke.gql.utils;

import com.wke.gql.greendao.bean.CityItem;
import com.wke.gql.greendao.bean.HistoryCityItem;

/**
 * Created by Administrator on 2018/3/13.
 */

public class BeanUtils {
    public static HistoryCityItem convert2HistoryCityItem(CityItem item) {
        HistoryCityItem a = new HistoryCityItem();
        a.airportCode = item.airportCode;
        a.airportEnName = item.airportEnName;
        a.airportCnName = item.airportCnName;
        a.airportPinyin = item.airportPinyin;
        a.airportPinyinShort = item.airportPinyinShort;
        a.airportNameSimple = item.airportNameSimple;
        a.airportEnNameSimple = item.airportEnNameSimple;
        a.cityCode = item.cityCode;
        a.cityCnName = item.cityCnName;
        a.cityEnName = item.cityEnName;
        a.cityPinyin = item.cityPinyin;
        a.cityPinyinShort = item.cityPinyinShort;
        a.countryCnName = item.countryCnName;
        a.countryEnName = item.countryEnName;
        a.continentCnName = item.continentCnName;
        a.continentEnName = item.continentEnName;
        a.latitude = item.latitude;
        a.longitude = item.longitude;
        a.isDomestic = item.isDomestic;
        a.isHot = item.isHot;
        return a;
    }

    public static CityItem convert2CityItem(HistoryCityItem item) {
        CityItem a = new CityItem();
        a.airportCode = item.airportCode;
        a.airportEnName = item.airportEnName;
        a.airportCnName = item.airportCnName;
        a.airportPinyin = item.airportPinyin;
        a.airportPinyinShort = item.airportPinyinShort;
        a.airportNameSimple = item.airportNameSimple;
        a.airportEnNameSimple = item.airportEnNameSimple;
        a.cityCode = item.cityCode;
        a.cityCnName = item.cityCnName;
        a.cityEnName = item.cityEnName;
        a.cityPinyin = item.cityPinyin;
        a.cityPinyinShort = item.cityPinyinShort;
        a.countryCnName = item.countryCnName;
        a.countryEnName = item.countryEnName;
        a.continentCnName = item.continentCnName;
        a.continentEnName = item.continentEnName;
        a.latitude = item.latitude;
        a.longitude = item.longitude;
        a.isDomestic = item.isDomestic;
        a.isHot = item.isHot;
        return a;
    }
}
