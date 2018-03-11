package com.wke.gql.greendao.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * @author gql
 * @since 18-3-9.
 */
@Entity(nameInDb = "City")
public class CityItem extends BaseEntity {

    //    "airportCode": "OSL",
//            "airportEnname": "Oslo Gardermoen Airport",
//            "airportCnname": "奥斯陆机场",
//            "airportPinyin": "AoSiLuJiChang",
//            "airportPinyinShort": "ASLJC",
//            "airportNameSimple": "奥斯陆",
//            "airportEnNameSimple": "Oslo Gardermoen",
//            "cityCode": "OSL",
//            "cityCnname": "奥斯陆",
//            "cityEnname": "Oslo",
//            "cityPinyin": "aosilu",
//            "cityPinyinShort": "OSL",
//            "countryCnname": "挪威",
//            "countryEnname": "Norway",
//            "continentCnname": "欧洲",
//            "continentEnname": "Europe",
//            "latitude": 60.1975501,
//            "longitude": 11.1004153,
//            "isDomestic": false,
//            "isHot": false
    @Id(autoincrement = true)
    @Property(nameInDb = "ID")
    public Long airportId;
    @Property(nameInDb = "AIRPORT_CODE")
    public String airportCode;
    @Property(nameInDb = "AIRPORT_CNNAME")
    @SerializedName("airportCnname")
    public String airportCnName;
    @Property(nameInDb = "AIRPORT_ENNAME")
    @SerializedName("airportEnname")
    public String airportEnName;
    @Property(nameInDb = "AIRPORT_PINYIN")
    public String airportPinyin;
    @Property(nameInDb = "AIRPORT_PINYIN_SHORT")
    public String airportPinyinShort;
    @Property(nameInDb = "CITY_CODE")
    public String cityCode;
    @Property(nameInDb = "CITY_CNNAME")
    @SerializedName("cityCnname")
    public String cityCnName;
    @Property(nameInDb = "CITY_ENNAME")
    @SerializedName("cityEnname")
    public String cityEnName;
    @Property(nameInDb = "CITY_PINYIN")
    public String cityPinyin;
    @Property(nameInDb = "CITY_PINYIN_SHORT")
    public String cityPinyinShort;
    @Property(nameInDb = "COUNTRY_CNNAME")
    @SerializedName("countryCnname")
    public String countryCnName;
    @Property(nameInDb = "COUNTRY_ENNAME")
    @SerializedName("countryEnname")
    public String countryEnName;
    @Property(nameInDb = "CONTINENT_CNNAME")
    @SerializedName("continentCnname")
    public String continentCnName;
    @Property(nameInDb = "CONTINENT_ENNAME")
    @SerializedName("continentEnname")
    public String continentEnName;
    @Property(nameInDb = "LONGITUDE")
    public String longitude;
    @Property(nameInDb = "LATITUDE")
    public String latitude;
    @Property(nameInDb = "IS_DOMESTIC")
    public String isDomestic;
    @Property(nameInDb = "IS_HOT")
    public String isHot;

    @Property(nameInDb = "AIRPORT_NAME_SIMPLE")
    public String airportNameSimple;
    @Property(nameInDb = "AIRPORT_NAME_EN_SIMPLE")
    public String airportEnNameSimple;


    @Generated(hash = 780127632)
    public CityItem(Long airportId, String airportCode, String airportCnName,
                    String airportEnName, String airportPinyin, String airportPinyinShort,
                    String cityCode, String cityCnName, String cityEnName,
                    String cityPinyin, String cityPinyinShort, String countryCnName,
                    String countryEnName, String continentCnName, String continentEnName,
                    String longitude, String latitude, String isDomestic, String isHot,
                    String airportNameSimple, String airportEnNameSimple) {
        this.airportId = airportId;
        this.airportCode = airportCode;
        this.airportCnName = airportCnName;
        this.airportEnName = airportEnName;
        this.airportPinyin = airportPinyin;
        this.airportPinyinShort = airportPinyinShort;
        this.cityCode = cityCode;
        this.cityCnName = cityCnName;
        this.cityEnName = cityEnName;
        this.cityPinyin = cityPinyin;
        this.cityPinyinShort = cityPinyinShort;
        this.countryCnName = countryCnName;
        this.countryEnName = countryEnName;
        this.continentCnName = continentCnName;
        this.continentEnName = continentEnName;
        this.longitude = longitude;
        this.latitude = latitude;
        this.isDomestic = isDomestic;
        this.isHot = isHot;
        this.airportNameSimple = airportNameSimple;
        this.airportEnNameSimple = airportEnNameSimple;
    }


    @Generated(hash = 67413975)
    public CityItem() {
    }


    @Override
    public String toString() {
        return "CityItem{" +
                "airportId=" + airportId +
                ", airportCode='" + airportCode + '\'' +
                ", airportCnName='" + airportCnName + '\'' +
                ", airportEnName='" + airportEnName + '\'' +
                ", airportPinyin='" + airportPinyin + '\'' +
                ", airportPinyinShort='" + airportPinyinShort + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", cityCnName='" + cityCnName + '\'' +
                ", cityEnName='" + cityEnName + '\'' +
                ", cityPinyin='" + cityPinyin + '\'' +
                ", cityPinyinShort='" + cityPinyinShort + '\'' +
                ", countryCnName='" + countryCnName + '\'' +
                ", countryEnName='" + countryEnName + '\'' +
                ", continentCnName='" + continentCnName + '\'' +
                ", continentEnName='" + continentEnName + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", isDomestic=" + isDomestic +
                ", isHot=" + isHot +
                ", airportNameSimple='" + airportNameSimple + '\'' +
                ", airportEnNameSimple='" + airportEnNameSimple + '\'' +
                '}';
    }



    public String getAirportEnNameSimple() {
        return this.airportEnNameSimple;
    }


    public void setAirportEnNameSimple(String airportEnNameSimple) {
        this.airportEnNameSimple = airportEnNameSimple;
    }


    public String getAirportNameSimple() {
        return this.airportNameSimple;
    }


    public void setAirportNameSimple(String airportNameSimple) {
        this.airportNameSimple = airportNameSimple;
    }


    public String getIsHot() {
        return this.isHot;
    }


    public void setIsHot(String isHot) {
        this.isHot = isHot;
    }


    public String getIsDomestic() {
        return this.isDomestic;
    }


    public void setIsDomestic(String isDomestic) {
        this.isDomestic = isDomestic;
    }


    public String getLatitude() {
        return this.latitude;
    }


    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    public String getLongitude() {
        return this.longitude;
    }


    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getContinentEnName() {
        return this.continentEnName;
    }


    public void setContinentEnName(String continentEnName) {
        this.continentEnName = continentEnName;
    }


    public String getContinentCnName() {
        return this.continentCnName;
    }


    public void setContinentCnName(String continentCnName) {
        this.continentCnName = continentCnName;
    }


    public String getCountryEnName() {
        return this.countryEnName;
    }


    public void setCountryEnName(String countryEnName) {
        this.countryEnName = countryEnName;
    }


    public String getCountryCnName() {
        return this.countryCnName;
    }


    public void setCountryCnName(String countryCnName) {
        this.countryCnName = countryCnName;
    }


    public String getCityPinyinShort() {
        return this.cityPinyinShort;
    }


    public void setCityPinyinShort(String cityPinyinShort) {
        this.cityPinyinShort = cityPinyinShort;
    }


    public String getCityPinyin() {
        return this.cityPinyin;
    }


    public void setCityPinyin(String cityPinyin) {
        this.cityPinyin = cityPinyin;
    }


    public String getCityEnName() {
        return this.cityEnName;
    }


    public void setCityEnName(String cityEnName) {
        this.cityEnName = cityEnName;
    }


    public String getCityCnName() {
        return this.cityCnName;
    }


    public void setCityCnName(String cityCnName) {
        this.cityCnName = cityCnName;
    }


    public String getCityCode() {
        return this.cityCode;
    }


    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }


    public String getAirportPinyinShort() {
        return this.airportPinyinShort;
    }


    public void setAirportPinyinShort(String airportPinyinShort) {
        this.airportPinyinShort = airportPinyinShort;
    }


    public String getAirportPinyin() {
        return this.airportPinyin;
    }


    public void setAirportPinyin(String airportPinyin) {
        this.airportPinyin = airportPinyin;
    }


    public String getAirportEnName() {
        return this.airportEnName;
    }


    public void setAirportEnName(String airportEnName) {
        this.airportEnName = airportEnName;
    }


    public String getAirportCnName() {
        return this.airportCnName;
    }


    public void setAirportCnName(String airportCnName) {
        this.airportCnName = airportCnName;
    }


    public String getAirportCode() {
        return this.airportCode;
    }


    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }


    public Long getAirportId() {
        return this.airportId;
    }


    public void setAirportId(Long airportId) {
        this.airportId = airportId;
    }


}
