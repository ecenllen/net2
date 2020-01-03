package com.yydd.net.net.common.vo;


import com.yydd.net.net.constants.PayTypeEnum;

public class ConfirmOrderVO {
    private String orderNo;
    private String paymentData;
    private PayTypeEnum payType;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(String paymentData) {
        this.paymentData = paymentData;
    }

    public PayTypeEnum getPayType() {
        return payType;
    }

    public void setPayType(PayTypeEnum payType) {
        this.payType = payType;
    }
}
