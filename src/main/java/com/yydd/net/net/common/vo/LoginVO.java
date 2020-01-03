package com.yydd.net.net.common.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginVO {
    private String token;
    private String userName;
    private String userId;
    private List<UserFeatureVO> userFeatures;
    private Map<String, String> configs = new HashMap<>();

    public String getToken() {
        return token;
    }

    public String getUserId() {
        return userId;
    }

    public LoginVO setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<UserFeatureVO> getUserFeatures() {
        return userFeatures;
    }

    public void setUserFeatures(List<UserFeatureVO> userFeatures) {
        this.userFeatures = userFeatures;
    }

    public Map<String, String> getConfigs() {
        return configs;
    }

    public void setConfigs(Map<String, String> configs) {
        this.configs = configs;
    }

    public int getConfigInt(String configName, int defaultValue) {
        if (configs.containsKey(configName)) {
            return Integer.valueOf(configs.get(configName));
        } else {
            return defaultValue;
        }
    }

    public long getConfigLong(String configName, long defaultValue) {
        if (configs.containsKey(configName)) {
            return Long.valueOf(configs.get(configName));
        } else {
            return defaultValue;
        }
    }

    public String getConfig(String configName, String defaultValue) {
        if (configs.containsKey(configName)) {
            return configs.get(configName);
        } else {
            return defaultValue;
        }
    }

    public boolean getConfigBoolean(String configName, boolean defaultValue) {
        if (configs.containsKey(configName)) {
            return Boolean.parseBoolean(configs.get(configName));
        } else {
            return defaultValue;
        }
    }

    public double getConfigDouble(String configName, double defaultValue) {
        if (configs.containsKey(configName)) {
            return Long.valueOf(configs.get(configName));
        } else {
            return defaultValue;
        }
    }
}
