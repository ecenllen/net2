package com.yydd.net.net.common.vo;


import com.yydd.net.net.TimeUnitEnum;
import com.yydd.net.net.constants.FeatureEnum;

public class ProductFeatureVO {
    private long id;
    private String sku; //商品sku
    private FeatureEnum feature;
    private String title;
    private boolean limitAmount;
    private int amount;
    //数量说明
    private String amountDesc;
    //是否限制有效期
    private boolean limitExpireTime;
    //有效时间
    private int expireLength;
    //有效时间单位
    private TimeUnitEnum expireUnit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public FeatureEnum getFeature() {
        return feature;
    }

    public void setFeature(FeatureEnum feature) {
        this.feature = feature;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAmountDesc() {
        return amountDesc;
    }

    public void setAmountDesc(String amountDesc) {
        this.amountDesc = amountDesc;
    }

    public boolean isLimitExpireTime() {
        return limitExpireTime;
    }

    public void setLimitExpireTime(boolean limitExpireTime) {
        this.limitExpireTime = limitExpireTime;
    }

    public int getExpireLength() {
        return expireLength;
    }

    public void setExpireLength(int expireLength) {
        this.expireLength = expireLength;
    }

    public TimeUnitEnum getExpireUnit() {
        return expireUnit;
    }

    public void setExpireUnit(TimeUnitEnum expireUnit) {
        this.expireUnit = expireUnit;
    }



    public String formatFeature() {
        StringBuilder sb = new StringBuilder();
        sb.append(title);
        if (limitAmount) {
            sb.append(",  " + String.format(amountDesc, amount));
        }
        if (limitExpireTime) {
            sb.append(",  有效期" + expireLength + expireUnit.getDesc());
        }
        return sb.toString();
    }


}
