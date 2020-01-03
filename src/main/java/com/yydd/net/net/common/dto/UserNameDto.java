package com.yydd.net.net.common.dto;


import com.yydd.net.net.BaseDto;

/**
 * @Author: liaohaiping
 * @Description:
 * @Date: Created in 2019/6/10 0010 17:56
 */
public class UserNameDto extends BaseDto {
  private String userName;
  private String userId;

  public UserNameDto(String userName, String userId) {
    this.userName = userName;
    this.userId = userId;
  }

}
