package com.yydd.net.net.common.vo;


import com.yydd.net.net.constants.FeatureEnum;

import java.sql.Timestamp;


/**
 * 用户功能
 */
public class UserFeatureVO  {
    private long id;
    private String application; //应用程序
    private String userName; //用户名
    private FeatureEnum feature; //功能
    private boolean limitAmount;
    private int amount;
    private boolean limitExpireTime;
    private Timestamp expireTime;
    private Timestamp createTime;  //创建时间


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public FeatureEnum getFeature() {
        return feature;
    }

    public void setFeature(FeatureEnum feature) {
        this.feature = feature;
    }

    public boolean isLimitAmount() {
        return limitAmount;
    }

    public void setLimitAmount(boolean limitAmount) {
        this.limitAmount = limitAmount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isLimitExpireTime() {
        return limitExpireTime;
    }

    public void setLimitExpireTime(boolean limitExpireTime) {
        this.limitExpireTime = limitExpireTime;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public boolean isValid() {
        return (!limitAmount || amount > 0)
            && (!limitExpireTime || (expireTime != null && expireTime.getTime() > System
            .currentTimeMillis()));
    }

}
