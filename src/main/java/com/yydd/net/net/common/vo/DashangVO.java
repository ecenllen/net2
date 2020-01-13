package com.yydd.net.net.common.vo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DashangVO {

  private String userName;
  private String nickName;

  private String remark;

  private BigDecimal price;    //打赏

  private Timestamp createTime;

  public String getUserName() {
    return userName;
  }

  public DashangVO setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getNickName() {
    return nickName;
  }

  public DashangVO setNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public String getRemark() {
    return remark;
  }

  public DashangVO setRemark(String remark) {
    this.remark = remark;
    return this;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public DashangVO setPrice(BigDecimal price) {
    this.price = price;
    return this;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public DashangVO setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
    return this;
  }
}