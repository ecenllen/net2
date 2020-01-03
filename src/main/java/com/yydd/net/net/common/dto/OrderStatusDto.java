package com.yydd.net.net.common.dto;


import com.yydd.net.net.BaseDto;

public class OrderStatusDto extends BaseDto {
    public String orderNo;

    public OrderStatusDto(String orderNo) {
        this.orderNo = orderNo;
    }
}
