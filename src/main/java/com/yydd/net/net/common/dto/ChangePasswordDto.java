package com.yydd.net.net.common.dto;


public class ChangePasswordDto extends UserNameDto {
    public String password;
    public String newPassword;

    public ChangePasswordDto(String username, String userid, String password, String newPassword) {
        super(username, userid);
        this.password = password;
        this.newPassword = newPassword;
    }
}
