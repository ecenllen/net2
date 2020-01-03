package com.yydd.net.net.InterfaceManager;


import com.yydd.net.net.ApiResponse;
import com.yydd.net.net.AppExecutors;
import com.yydd.net.net.HttpUtils;
import com.yydd.net.net.common.CommonApiService;
import com.yydd.net.net.common.dto.ChangePasswordDto;
import com.yydd.net.net.common.dto.RegisterUserDto;
import com.yydd.net.net.constants.Constant;
import com.yydd.net.net.event.RegisterEvent;
import com.yydd.net.net.event.ResetPasswordEvent;
import com.yydd.net.net.event.TokenEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yingyongduoduo on 2019/6/24.
 */

public class RegisterInterface {

    public static void loadRegister(String username, String password) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse register = HttpUtils.getInstance().getService(CommonApiService.class)
                    .register(new RegisterUserDto(username, password));
            // 900 token 过期
            if (register.success()) {
                EventBus.getDefault().post(new RegisterEvent().setSucceed(register.success()));
            } else if (Constant.TOKEN_CODE == register.getCode()) {
                EventBus.getDefault().post(new TokenEvent());
            } else {
                EventBus.getDefault().post(new RegisterEvent().setSucceed(register.success()).setMsg(register.getMessage()).setCode(register.getCode()));
            }
        });
    }

    public static void resetPassword(String username, String userid, String password, String newpassword) {
        AppExecutors.runNetworkIO(() -> {
            ApiResponse register = HttpUtils.getInstance().getService(CommonApiService.class)
                    .changePassword(new ChangePasswordDto(username, userid, password, newpassword));
            // 900 token 过期
            if (register.success()) {
                EventBus.getDefault().post(new ResetPasswordEvent().setSucceed(true));
            } else if (Constant.TOKEN_CODE == register.getCode()) {
                EventBus.getDefault().post(new TokenEvent());
            } else {
                EventBus.getDefault().post(new ResetPasswordEvent().setSucceed(false));
            }
        });
    }
}
