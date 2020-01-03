package com.yydd.net.net;

import java.util.Calendar;

public enum TimeUnitEnum {
  MINUTE("分钟"),
  HOUR("小时"),
  DAY("天"),
  WEEK("周"),
  MONTH("个月"),
  YEAR("年"),;

  private String desc;

  TimeUnitEnum(String desc) {
    this.desc = desc;
  }


  public String getDesc() {
    return desc;
  }

  public TimeUnitEnum setDesc(String desc) {
    this.desc = desc;
    return this;
  }

  public int toCalendarField() {
    switch (this) {
      case MINUTE:
        return Calendar.MINUTE;
      case HOUR:
        return Calendar.HOUR_OF_DAY;
      case DAY:
        return Calendar.DAY_OF_MONTH;
      case WEEK:
        return Calendar.WEEK_OF_MONTH;
      case MONTH:
        return Calendar.MONTH;
      case YEAR:
        return Calendar.YEAR;
      default:
        return Calendar.DAY_OF_MONTH;
    }
  }
}
