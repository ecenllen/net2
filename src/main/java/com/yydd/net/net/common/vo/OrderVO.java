package com.yydd.net.net.common.vo;


import com.yydd.net.net.constants.PayStatusEnum;
import com.yydd.net.net.constants.PayTypeEnum;

import java.math.BigDecimal;

public class OrderVO {
    private String application; //应用程序
    private String userName; //用户名
    private String sku; //sku  商品
    private String orderNo; // 订单号
    private String outOrderNo;//第三方支付订单
    private String orderTitle; //订单名称
    private BigDecimal price;    //订单总额
    private PayStatusEnum payStatus;  //支付状态
    private PayTypeEnum payType;  //支付类型

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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOutOrderNo() {
        return outOrderNo;
    }

    public void setOutOrderNo(String outOrderNo) {
        this.outOrderNo = outOrderNo;
    }

    public String getOrderTitle() {
        return orderTitle;
    }

    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PayStatusEnum getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(PayStatusEnum payStatus) {
        this.payStatus = payStatus;
    }

    public PayTypeEnum getPayType() {
        return payType;
    }

    public void setPayType(PayTypeEnum payType) {
        this.payType = payType;
    }
}
