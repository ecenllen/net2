package com.yydd.net.net;


import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.yydd.net.net.common.vo.LoginVO;
import com.yydd.net.net.common.vo.UserFeatureVO;
import com.yydd.net.net.common.vo.UserPassword;
import com.yydd.net.net.constants.FeatureEnum;
import com.yydd.net.net.constants.SysConfigEnum;
import com.yydd.net.net.util.GsonUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Map;

public class CacheUtils {
    private static String SHARED_PREFERENCE_KEY;
    private static Context context;

    public static void init(Context ctx) {
        context = ctx;
        SHARED_PREFERENCE_KEY = ctx.getPackageName();
    }


    private static SharedPreferences getSharedPreferences(String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    private static SharedPreferences getPublicPreferences() {
        return getSharedPreferences(SHARED_PREFERENCE_KEY);
    }

    public static void setConfigs(Map<String, String> configs) {
        SharedPreferences.Editor edit = getPublicPreferences().edit();
        for (String key : configs.keySet()) {
            String value = configs.get(key);
            edit.putString(key, value);
        }
        edit.apply();
    }

    public static void setLoginData(LoginVO loginData) {
        String json = GsonUtil.toJson(loginData);
        getPublicPreferences().edit()
                .putString("loginData", json)
                .putString("loginData.token", loginData.getToken())
                .apply();

    }

    public static void setUserNamePassword(String userName, String password) {
        getPublicPreferences().edit()
                .putString("login.userName", userName)
                .putString("login.password", password)
                .apply();
    }

    public static UserPassword getUserPassword() {
        SharedPreferences publicPreferences = getPublicPreferences();
        if (publicPreferences.contains("login.userName")) {
            String userName = publicPreferences.getString("login.userName", "");
            String password = publicPreferences.getString("login.password", "");
            return new UserPassword(userName, password);
        }
        return new UserPassword();

    }

    public static LoginVO getLoginData() {
        SharedPreferences preferences = getPublicPreferences();
        if (!preferences.contains("loginData")) {
            return new LoginVO();
        }
        String json = preferences.getString("loginData", "");
        if (TextUtils.isEmpty(json)) {
            return new LoginVO();
        }

        return GsonUtil.fromJson(json, new TypeToken<LoginVO>() {
        }.getType());
    }

    public static boolean isLogin() {
        String token = getToken();
        if (TextUtils.isEmpty(token)) {
            return false;
        }
        String[] arr = token.split("\\.");
        if (arr.length != 3) {
            return false;
        }
        byte[] bytes = Base64.decode(arr[1], Base64.DEFAULT);
        //        {"jti":"lhp","sub":"TEXT2VOICE","iss":"com.xbq.webapi","iat":1560650388,"exp":1560657588}
        String json = Charset.forName("utf-8").decode(ByteBuffer.wrap(bytes)).toString();
        try {
            Log.d("lhp", "token payload: " + json);
            JSONObject jsonObject = new JSONObject(json);
            long expireTime = jsonObject.optLong("exp", TimeUtils.getTimeAfterNow(1, TimeUnitEnum.DAY).getTime());
            return expireTime * 1000 > System.currentTimeMillis();

        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void exitLogin() {
        getPublicPreferences().edit()
                .clear()
                .commit();
    }


    public static String getToken() {
        return getPublicPreferences().getString("loginData.token", "");
    }


    public static boolean canUse(FeatureEnum featureEnum) {
        LoginVO loginData = getLoginData();
        UserFeatureVO feature = Linq.of(loginData.getUserFeatures()).first(f -> f.getFeature().equals(featureEnum));
        if (feature != null && feature.isValid()) {
            return true;
        }
        return false;
    }

    public static String getConfig(String configName, String defaultValue) {
        return getPublicPreferences().getString(configName, defaultValue);
    }


    public static String getConfig(SysConfigEnum configEnum) {
        return getPublicPreferences().getString(configEnum.getKeyName(), configEnum.getValue());
    }

    public static int getConfigInt(String configName, int defaultValue) {
        String config = getConfig(configName, String.valueOf(defaultValue));
        try {
            return Integer.valueOf(config);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static int getConfigInt(SysConfigEnum configEnum) {
        return getConfigInt(configEnum.getKeyName(), configEnum.getValueInt());
    }

    public static boolean getConfigBoolean(String configName, boolean defaultValue) {
        String config = getConfig(configName, String.valueOf(defaultValue));
        try {
            return Boolean.valueOf(config);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static boolean getConfigBoolean(SysConfigEnum configEnum) {
        return getConfigBoolean(configEnum.getKeyName(), configEnum.getValueBoolean());
    }

//    public static String getConfigString(String configName, String defaultValue) {
//        String config = getConfig(configName, defaultValue);
//        try {
//            return config;
//        } catch (Exception e) {
//            return defaultValue;
//        }
//    }

//    public static String getConfigString(SysConfigEnum configEnum) {
//        return getConfigString(configEnum.getKeyName(), configEnum.getValue());
//    }

    public static void save(String key, String value) {
        getPublicPreferences()
                .edit()
                .putString(key, value)
                .apply();
    }

}
