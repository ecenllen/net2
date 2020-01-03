package com.yydd.net.net.constants;

/**
 * @Author: liaohaiping
 * @Description: 时间长度单位
 * @Date: Created in 2019/3/18 0018 15:57
 */
public enum PayStatusEnum {
  PENDING("待支付"),
  PAID("已支付"),
  REFUNDED("已退款"),
  CLOSED("交易已关闭(未支付)"),
  ;

  private String desc;

  PayStatusEnum(String desc) {
    this.desc = desc;
  }


  public String getDesc() {
    return desc;
  }

  public PayStatusEnum setDesc(String desc) {
    this.desc = desc;
    return this;
  }
}
