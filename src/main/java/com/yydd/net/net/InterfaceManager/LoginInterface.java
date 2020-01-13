package com.yydd.net.net.InterfaceManager;


import com.yydd.net.net.ApiResponse;
import com.yydd.net.net.AppExecutors;
import com.yydd.net.net.BaseDto;
import com.yydd.net.net.CacheUtils;
import com.yydd.net.net.DataResponse;
import com.yydd.net.net.HttpUtils;
import com.yydd.net.net.common.CommonApiService;
import com.yydd.net.net.common.dto.RegisterUserDto;
import com.yydd.net.net.common.vo.LoginVO;
import com.yydd.net.net.common.vo.UserFeatureVO;
import com.yydd.net.net.event.LoginEvent;
import com.yydd.net.net.event.ResetLoginEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

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

    public static void addOldVip() {
        AppExecutors.runNetworkIO(new Runnable() {
            @Override
            public void run() {
                ApiResponse apiResponse = HttpUtils.getInstance().getService(CommonApiService.class).addOldVip(new BaseDto());
                if (apiResponse.success()) {
                    resetLoginDate();
                } else {

                }
            }
        });
    }

    public static void resetLoginDate() {
        AppExecutors.runNetworkIO(new Runnable() {
            @Override
            public void run() {
                DataResponse<List<UserFeatureVO>> listDataResponse = HttpUtils.getInstance().getService(CommonApiService.class)
                        .userFeatures(new BaseDto());
                if (listDataResponse.success()) {
                    LoginVO loginData = CacheUtils.getLoginData();
                    loginData.setUserFeatures(listDataResponse.getData());
                    CacheUtils.setLoginData(loginData);
                }
                EventBus.getDefault().post(new ResetLoginEvent().setSuccess(listDataResponse.success()));
            }
        });

    }


    public static void registerLogin(String username, String password) {
        AppExecutors.runNetworkIO(() -> {
            DataResponse<LoginVO> login = HttpUtils.getInstance().getService(CommonApiService.class)
                    .registerLogin(new RegisterUserDto(username, password));

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
