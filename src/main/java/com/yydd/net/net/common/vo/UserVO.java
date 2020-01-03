package com.yydd.net.net.common.vo;

/**
 * @Author: liaohaiping
 * @Description:
 * @Date: Created in 2019/6/24 0024 17:55
 */
public class UserVO {

  private long id;

  private String userName; //用户名

  private String email;

  private String phone;

  private int goldCoin; //金币

  private String friendRemark;//好友备注

  public long getId() {
    return id;
  }

  public UserVO setId(long id) {
    this.id = id;
    return this;
  }

  public String getUserName() {
    return userName;
  }

  public UserVO setUserName(String userName) {
    this.userName = userName;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public UserVO setEmail(String email) {
    this.email = email;
    return this;
  }

  public String getPhone() {
    return phone;
  }

  public UserVO setPhone(String phone) {
    this.phone = phone;
    return this;
  }

  public int getGoldCoin() {
    return goldCoin;
  }

  public UserVO setGoldCoin(int goldCoin) {
    this.goldCoin = goldCoin;
    return this;
  }

  public String getFriendRemark() {
    return friendRemark;
  }

  public UserVO setFriendRemark(String friendRemark) {
    this.friendRemark = friendRemark;
    return this;
  }
}
