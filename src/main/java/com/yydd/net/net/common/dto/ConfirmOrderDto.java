package com.yydd.net.net.common.dto;


import com.yydd.net.net.BaseDto;
import com.yydd.net.net.constants.PayTypeEnum;

import java.math.BigDecimal;

public class ConfirmOrderDto extends BaseDto {
    public String sku;
    public PayTypeEnum payType;  //支付类型
    public String contactPhone;
    public String payDesc; //有支付宝无微信
    public BigDecimal userPrice;
    public String orderAttr;

    public ConfirmOrderDto(String sku, PayTypeEnum payType, String contactPhone, String payDesc, BigDecimal userPrice, String orderAttr) {
        this.sku = sku;
        this.payType = payType;
        this.contactPhone = contactPhone;
        this.payDesc = payDesc;
        this.orderAttr = orderAttr;
        this.userPrice = userPrice;
    }
}
