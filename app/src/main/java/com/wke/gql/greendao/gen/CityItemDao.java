package com.wke.gql.greendao.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.wke.gql.greendao.bean.CityItem;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "City".
 */
public class CityItemDao extends AbstractDao<CityItem, Long> {

    public static final String TABLENAME = "City";

    public CityItemDao(DaoConfig config) {
        super(config);
    }

    ;


    public CityItemDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"City\" (" + //
                "\"ID\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: airportId
                "\"AIRPORT_CODE\" TEXT," + // 1: airportCode
                "\"AIRPORT_CNNAME\" TEXT," + // 2: airportCnName
                "\"AIRPORT_ENNAME\" TEXT," + // 3: airportEnName
                "\"AIRPORT_PINYIN\" TEXT," + // 4: airportPinyin
                "\"AIRPORT_PINYIN_SHORT\" TEXT," + // 5: airportPinyinShort
                "\"CITY_CODE\" TEXT," + // 6: cityCode
                "\"CITY_CNNAME\" TEXT," + // 7: cityCnName
                "\"CITY_ENNAME\" TEXT," + // 8: cityEnName
                "\"CITY_PINYIN\" TEXT," + // 9: cityPinyin
                "\"CITY_PINYIN_SHORT\" TEXT," + // 10: cityPinyinShort
                "\"COUNTRY_CNNAME\" TEXT," + // 11: countryCnName
                "\"COUNTRY_ENNAME\" TEXT," + // 12: countryEnName
                "\"CONTINENT_CNNAME\" TEXT," + // 13: continentCnName
                "\"CONTINENT_ENNAME\" TEXT," + // 14: continentEnName
                "\"LONGITUDE\" TEXT," + // 15: longitude
                "\"LATITUDE\" TEXT," + // 16: latitude
                "\"IS_DOMESTIC\" TEXT," + // 17: isDomestic
                "\"IS_HOT\" TEXT," + // 18: isHot
                "\"AIRPORT_NAME_SIMPLE\" TEXT," + // 19: airportNameSimple
                "\"AIRPORT_NAME_EN_SIMPLE\" TEXT);"); // 20: airportEnNameSimple
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"City\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CityItem entity) {
        stmt.clearBindings();

        Long airportId = entity.getAirportId();
        if (airportId != null) {
            stmt.bindLong(1, airportId);
        }

        String airportCode = entity.getAirportCode();
        if (airportCode != null) {
            stmt.bindString(2, airportCode);
        }

        String airportCnName = entity.getAirportCnName();
        if (airportCnName != null) {
            stmt.bindString(3, airportCnName);
        }

        String airportEnName = entity.getAirportEnName();
        if (airportEnName != null) {
            stmt.bindString(4, airportEnName);
        }

        String airportPinyin = entity.getAirportPinyin();
        if (airportPinyin != null) {
            stmt.bindString(5, airportPinyin);
        }

        String airportPinyinShort = entity.getAirportPinyinShort();
        if (airportPinyinShort != null) {
            stmt.bindString(6, airportPinyinShort);
        }

        String cityCode = entity.getCityCode();
        if (cityCode != null) {
            stmt.bindString(7, cityCode);
        }

        String cityCnName = entity.getCityCnName();
        if (cityCnName != null) {
            stmt.bindString(8, cityCnName);
        }

        String cityEnName = entity.getCityEnName();
        if (cityEnName != null) {
            stmt.bindString(9, cityEnName);
        }

        String cityPinyin = entity.getCityPinyin();
        if (cityPinyin != null) {
            stmt.bindString(10, cityPinyin);
        }

        String cityPinyinShort = entity.getCityPinyinShort();
        if (cityPinyinShort != null) {
            stmt.bindString(11, cityPinyinShort);
        }

        String countryCnName = entity.getCountryCnName();
        if (countryCnName != null) {
            stmt.bindString(12, countryCnName);
        }

        String countryEnName = entity.getCountryEnName();
        if (countryEnName != null) {
            stmt.bindString(13, countryEnName);
        }

        String continentCnName = entity.getContinentCnName();
        if (continentCnName != null) {
            stmt.bindString(14, continentCnName);
        }

        String continentEnName = entity.getContinentEnName();
        if (continentEnName != null) {
            stmt.bindString(15, continentEnName);
        }

        String longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindString(16, longitude);
        }

        String latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindString(17, latitude);
        }

        String isDomestic = entity.getIsDomestic();
        if (isDomestic != null) {
            stmt.bindString(18, isDomestic);
        }

        String isHot = entity.getIsHot();
        if (isHot != null) {
            stmt.bindString(19, isHot);
        }

        String airportNameSimple = entity.getAirportNameSimple();
        if (airportNameSimple != null) {
            stmt.bindString(20, airportNameSimple);
        }

        String airportEnNameSimple = entity.getAirportEnNameSimple();
        if (airportEnNameSimple != null) {
            stmt.bindString(21, airportEnNameSimple);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CityItem entity) {
        stmt.clearBindings();

        Long airportId = entity.getAirportId();
        if (airportId != null) {
            stmt.bindLong(1, airportId);
        }

        String airportCode = entity.getAirportCode();
        if (airportCode != null) {
            stmt.bindString(2, airportCode);
        }

        String airportCnName = entity.getAirportCnName();
        if (airportCnName != null) {
            stmt.bindString(3, airportCnName);
        }

        String airportEnName = entity.getAirportEnName();
        if (airportEnName != null) {
            stmt.bindString(4, airportEnName);
        }

        String airportPinyin = entity.getAirportPinyin();
        if (airportPinyin != null) {
            stmt.bindString(5, airportPinyin);
        }

        String airportPinyinShort = entity.getAirportPinyinShort();
        if (airportPinyinShort != null) {
            stmt.bindString(6, airportPinyinShort);
        }

        String cityCode = entity.getCityCode();
        if (cityCode != null) {
            stmt.bindString(7, cityCode);
        }

        String cityCnName = entity.getCityCnName();
        if (cityCnName != null) {
            stmt.bindString(8, cityCnName);
        }

        String cityEnName = entity.getCityEnName();
        if (cityEnName != null) {
            stmt.bindString(9, cityEnName);
        }

        String cityPinyin = entity.getCityPinyin();
        if (cityPinyin != null) {
            stmt.bindString(10, cityPinyin);
        }

        String cityPinyinShort = entity.getCityPinyinShort();
        if (cityPinyinShort != null) {
            stmt.bindString(11, cityPinyinShort);
        }

        String countryCnName = entity.getCountryCnName();
        if (countryCnName != null) {
            stmt.bindString(12, countryCnName);
        }

        String countryEnName = entity.getCountryEnName();
        if (countryEnName != null) {
            stmt.bindString(13, countryEnName);
        }

        String continentCnName = entity.getContinentCnName();
        if (continentCnName != null) {
            stmt.bindString(14, continentCnName);
        }

        String continentEnName = entity.getContinentEnName();
        if (continentEnName != null) {
            stmt.bindString(15, continentEnName);
        }

        String longitude = entity.getLongitude();
        if (longitude != null) {
            stmt.bindString(16, longitude);
        }

        String latitude = entity.getLatitude();
        if (latitude != null) {
            stmt.bindString(17, latitude);
        }

        String isDomestic = entity.getIsDomestic();
        if (isDomestic != null) {
            stmt.bindString(18, isDomestic);
        }

        String isHot = entity.getIsHot();
        if (isHot != null) {
            stmt.bindString(19, isHot);
        }

        String airportNameSimple = entity.getAirportNameSimple();
        if (airportNameSimple != null) {
            stmt.bindString(20, airportNameSimple);
        }

        String airportEnNameSimple = entity.getAirportEnNameSimple();
        if (airportEnNameSimple != null) {
            stmt.bindString(21, airportEnNameSimple);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    @Override
    public CityItem readEntity(Cursor cursor, int offset) {
        CityItem entity = new CityItem( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // airportId
                cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // airportCode
                cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // airportCnName
                cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // airportEnName
                cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // airportPinyin
                cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // airportPinyinShort
                cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // cityCode
                cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // cityCnName
                cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // cityEnName
                cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // cityPinyin
                cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // cityPinyinShort
                cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // countryCnName
                cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // countryEnName
                cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // continentCnName
                cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // continentEnName
                cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // longitude
                cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // latitude
                cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // isDomestic
                cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18), // isHot
                cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19), // airportNameSimple
                cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20) // airportEnNameSimple
        );
        return entity;
    }

    @Override
    public void readEntity(Cursor cursor, CityItem entity, int offset) {
        entity.setAirportId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAirportCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAirportCnName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setAirportEnName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAirportPinyin(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setAirportPinyinShort(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setCityCode(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setCityCnName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setCityEnName(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCityPinyin(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setCityPinyinShort(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setCountryCnName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCountryEnName(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setContinentCnName(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setContinentEnName(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setLongitude(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setLatitude(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setIsDomestic(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setIsHot(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
        entity.setAirportNameSimple(cursor.isNull(offset + 19) ? null : cursor.getString(offset + 19));
        entity.setAirportEnNameSimple(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
     }
     
    @Override
    protected final Long updateKeyAfterInsert(CityItem entity, long rowId) {
        entity.setAirportId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CityItem entity) {
        if (entity != null) {
            return entity.getAirportId();
        } else {
            return null;
        }
    }
    
    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }

/**
     * Properties of entity CityItem.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property AirportId = new Property(0, Long.class, "airportId", true, "ID");
        public final static Property AirportCode = new Property(1, String.class, "airportCode", false, "AIRPORT_CODE");
        public final static Property AirportCnName = new Property(2, String.class, "airportCnName", false, "AIRPORT_CNNAME");
        public final static Property AirportEnName = new Property(3, String.class, "airportEnName", false, "AIRPORT_ENNAME");
        public final static Property AirportPinyin = new Property(4, String.class, "airportPinyin", false, "AIRPORT_PINYIN");
        public final static Property AirportPinyinShort = new Property(5, String.class, "airportPinyinShort", false, "AIRPORT_PINYIN_SHORT");
        public final static Property CityCode = new Property(6, String.class, "cityCode", false, "CITY_CODE");
        public final static Property CityCnName = new Property(7, String.class, "cityCnName", false, "CITY_CNNAME");
        public final static Property CityEnName = new Property(8, String.class, "cityEnName", false, "CITY_ENNAME");
        public final static Property CityPinyin = new Property(9, String.class, "cityPinyin", false, "CITY_PINYIN");
        public final static Property CityPinyinShort = new Property(10, String.class, "cityPinyinShort", false, "CITY_PINYIN_SHORT");
        public final static Property CountryCnName = new Property(11, String.class, "countryCnName", false, "COUNTRY_CNNAME");
        public final static Property CountryEnName = new Property(12, String.class, "countryEnName", false, "COUNTRY_ENNAME");
        public final static Property ContinentCnName = new Property(13, String.class, "continentCnName", false, "CONTINENT_CNNAME");
        public final static Property ContinentEnName = new Property(14, String.class, "continentEnName", false, "CONTINENT_ENNAME");
        public final static Property Longitude = new Property(15, String.class, "longitude", false, "LONGITUDE");
        public final static Property Latitude = new Property(16, String.class, "latitude", false, "LATITUDE");
    public final static Property IsDomestic = new Property(17, String.class, "isDomestic", false, "IS_DOMESTIC");
    public final static Property IsHot = new Property(18, String.class, "isHot", false, "IS_HOT");
    public final static Property AirportNameSimple = new Property(19, String.class, "airportNameSimple", false, "AIRPORT_NAME_SIMPLE");
    public final static Property AirportEnNameSimple = new Property(20, String.class, "airportEnNameSimple", false, "AIRPORT_NAME_EN_SIMPLE");
    }
    
}
