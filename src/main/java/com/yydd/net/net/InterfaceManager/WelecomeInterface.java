package com.yydd.net.net.InterfaceManager;


import com.yydd.net.net.AppExecutors;
import com.yydd.net.net.HttpUtils;
import com.yydd.net.net.common.CommonApiService;
import com.yydd.net.net.common.dto.ApplicationDto;


/**
 * Created by yingyongduoduo on 2019/7/1.
 */

public class WelecomeInterface {
    public static void newDeviceUser() {
        AppExecutors.runNetworkIO(() -> {
            HttpUtils.getInstance().getService(CommonApiService.class).newDeviceUser(new ApplicationDto()); //统计用户
        });
    }
}
