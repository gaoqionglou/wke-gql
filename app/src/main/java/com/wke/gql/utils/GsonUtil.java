package com.wke.gql.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
    private static Gson gson;
    public static Gson getGsonInstance(){
        if(gson==null){
            gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss") //设置date转换的固定格式
                    .create();
        }
        return gson;
    }


    public static <T> T fromJson(String json, Class<T> clas){
        return getGsonInstance().fromJson(json,clas);
    }

    public static <T> String toJson(T obj){
        return getGsonInstance().toJson(obj);
    }
}
