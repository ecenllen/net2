package com.yydd.net.net.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yydd.net.net.util.adapter.BooleanTypeAdapter;
import com.yydd.net.net.util.adapter.DoubleTypeAdapter;
import com.yydd.net.net.util.adapter.IntegerTypeAdapter;
import com.yydd.net.net.util.adapter.LongTypeAdapter;

import java.lang.reflect.Type;

/**
 * Created by Shanlin on 2017/4/5.
 */

public class GsonUtil {

    public static Gson gson() {
        return new GsonBuilder()
                .serializeNulls()
                .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                .registerTypeAdapter(int.class, new IntegerTypeAdapter())
                .registerTypeAdapter(Boolean.class, new BooleanTypeAdapter())
                .registerTypeAdapter(boolean.class, new BooleanTypeAdapter())
                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                .registerTypeAdapter(Long.class, new LongTypeAdapter())
                .registerTypeAdapter(long.class, new LongTypeAdapter())
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
    }

    public static String toJson(Object object) {
        Gson gson = gson();
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> clz) {
        Gson gson = gson();
        return gson.fromJson(json, clz);
    }

    public static <T> T fromJson(String json, Type typeoft) {
        Gson gson = gson();
        return gson.fromJson(json, typeoft);
    }
}
