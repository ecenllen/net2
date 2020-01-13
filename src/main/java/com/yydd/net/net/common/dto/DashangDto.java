package com.yydd.net.net.common.dto;

/**
 * Created by yingyongduoduo on 2019/7/10.
 */

public class DashangDto {
    private String nickName;
    private String remark;

    public String getNickName() {
        return nickName;
    }

    public DashangDto setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public DashangDto setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
