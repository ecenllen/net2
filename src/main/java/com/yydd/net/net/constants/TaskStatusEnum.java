package com.yydd.net.net.constants;

public enum TaskStatusEnum {
  PENDING("待处理"),
  SUCCESS("处理成功"),
  FAILED("处理失败"),
  ;
  String desc;

  TaskStatusEnum(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
