package com.yydd.net.net.help;


import com.yydd.net.net.CacheUtils;
import com.yydd.net.net.constants.FeatureEnum;

/**
 * Created by yingyongduoduo on 2019/7/4.
 */

public class FreeExpireHelp {

    public static boolean isNeedPay() {
            return CacheUtils.getLoginData().getConfigBoolean("ischarge", false) && !CacheUtils.canUse(FeatureEnum.RED_PACKET);
//        return true;
    }
}
