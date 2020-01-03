package com.yydd.net.net.constants;

/**
 * @Author: liaohaiping
 * @Description: 时间长度单位
 * @Date: Created in 2019/3/18 0018 15:57
 */
public enum PayTypeEnum {
  ALIPAY_APP("支付宝app支付"),
  WXPAY_APP("微信app支付"),
  OFFLINE("线下支付"),
  ;

  private String desc;

  PayTypeEnum(String desc) {
    this.desc = desc;
  }


  public String getDesc() {
    return desc;
  }

  public PayTypeEnum setDesc(String desc) {
    this.desc = desc;
    return this;
  }
}
