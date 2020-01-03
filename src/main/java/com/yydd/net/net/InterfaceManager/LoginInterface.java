package com.yydd.net.net.InterfaceManager;


import com.yydd.net.net.AppExecutors;
import com.yydd.net.net.CacheUtils;
import com.yydd.net.net.DataResponse;
import com.yydd.net.net.HttpUtils;
import com.yydd.net.net.common.CommonApiService;
import com.yydd.net.net.common.dto.RegisterUserDto;
import com.yydd.net.net.common.vo.LoginVO;
import com.yydd.net.net.event.LoginEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yingyongduoduo on 2019/6/24.
 */

public class LoginInterface {

    public static void Login(String username, String password) {
        AppExecutors.runNetworkIO(() -> {
            DataResponse<LoginVO> login = HttpUtils.getInstance().getService(CommonApiService.class)
                    .login(new RegisterUserDto(username, password));

            if (login.success()) {
                CacheUtils.setLoginData(login.getData());
                CacheUtils.setUserNamePassword(username, password);
                EventBus.getDefault().post(new LoginEvent().setSucceed(login.success()));
            } else {
                CacheUtils.setLoginData(new LoginVO());
                EventBus.getDefault().post(new LoginEvent().setSucceed(login.success()).setMsg(login.getMessage()));
            }
        });
    }

}
