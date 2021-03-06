package com.yydd.net.net.constants;

public enum FeatureEnum {
    RED_PACKET("抢红包"),
    MIRROR("镜子"),
    BEIDOU("北斗会员");


    private String desc;

    FeatureEnum(String desc) {
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public FeatureEnum setDesc(String desc) {
        this.desc = desc;
        return this;
    }
}
