package com.yydd.net.net.common.dto;


import com.yydd.net.net.BaseDto;

public class RegisterUserDto extends BaseDto {
    public String userName;
    public String password;

    public RegisterUserDto() {
    }

    public RegisterUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}