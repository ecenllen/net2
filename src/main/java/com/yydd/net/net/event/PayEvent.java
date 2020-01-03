package com.yydd.net.net.event;

/**
 * Created by yingyongduoduo on 2019/6/29.
 */

public class PayEvent {
    private boolean succeed;
    private String msg;
    private String orderNo;

    public String getMsg() {
        return msg;
    }

    public PayEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public PayEvent setSucceed(boolean succeed) {
        this.succeed = succeed;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public PayEvent setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }
}
