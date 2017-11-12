package com.wke.gql.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.wke.gql.base.BaseApplication;

import java.util.Map;



public class SharePerfenceUtil {
    public static void save(String key, Object value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication());
        if (value instanceof Boolean) sp.edit().putBoolean(key, (Boolean) value).apply();
        else if (value instanceof Float) sp.edit().putFloat(key, (Float) value).apply();
        else if (value instanceof Integer) sp.edit().putInt(key, (Integer) value).apply();
        else if (value instanceof Long) sp.edit().putLong(key, (Long) value).apply();
        else if (value instanceof String) sp.edit().putString(key, (String) value).apply();
    }

    public static Boolean getBoolean(String key, Boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication()).getBoolean(key, defaultValue);
    }

    public static Float getFloat(String key, Float defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication()).getFloat(key, defaultValue);
    }

    public static Integer getInt(String key, Integer defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication()).getInt(key, defaultValue);
    }

    public static Long getLong(String key, Long defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication()).getLong(key, defaultValue);
    }

    public static String getString(String key, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication()).getString(key, defaultValue);
    }

    public static Map<String, ?> getAll() {
        return PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication()).getAll();
    }

    public static void deleteData(String key) {
        PreferenceManager.getDefaultSharedPreferences(BaseApplication.getApplication()).edit().remove(key).apply();
    }
}
