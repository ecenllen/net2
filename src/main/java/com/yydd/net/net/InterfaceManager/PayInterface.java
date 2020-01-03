package com.yydd.net.net.InterfaceManager;

import android.app.Activity;
import android.content.Context;

import com.yydd.net.net.AppExecutors;
import com.yydd.net.net.BaseDto;
import com.yydd.net.net.CacheUtils;
import com.yydd.net.net.DataResponse;
import com.yydd.net.net.HttpUtils;
import com.yydd.net.net.PayDao;
import com.yydd.net.net.common.CommonApiService;
import com.yydd.net.net.common.dto.ConfirmOrderDto;
import com.yydd.net.net.common.dto.ProductListDto;
import com.yydd.net.net.common.vo.ConfirmOrderVO;
import com.yydd.net.net.common.vo.LoginVO;
import com.yydd.net.net.common.vo.ProductVO;
import com.yydd.net.net.common.vo.UserFeatureVO;
import com.yydd.net.net.constants.Constant;
import com.yydd.net.net.constants.FeatureEnum;
import com.yydd.net.net.constants.PayTypeEnum;
import com.yydd.net.net.event.PayEvent;
import com.yydd.net.net.event.TokenEvent;
import com.yydd.net.net.util.PublicUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by yingyongduoduo on 2019/6/24.
 */

public class PayInterface {

    public static void getProductList() {
        AppExecutors.runNetworkIO(() -> {
            List<ProductVO> data = HttpUtils.getInstance().getService(CommonApiService.class)
                    .productList(new ProductListDto(FeatureEnum.RED_PACKET))
                    .getData();
            EventBus.getDefault().post(data == null ? new ArrayList<ProductVO>() : data);
        });
    }


    public static void pay(Activity activity, ProductVO productVO, PayTypeEnum payTypeEnum, String contactPhone) {
        AppExecutors.runNetworkIO(new Runnable() {
            @Override
            public void run() {
                DataResponse<ConfirmOrderVO> confirmOrderVODataResponse = HttpUtils.getInstance().getService(CommonApiService.class)
                        .confirmOrder(new ConfirmOrderDto(productVO.getSku(), payTypeEnum, contactPhone, getPaydesc(activity), productVO.getPrice(), ""));
                if (confirmOrderVODataResponse.success()) {
                    if (payTypeEnum == PayTypeEnum.ALIPAY_APP)
                        PayDao.getInstance().setActivity(activity).goAlipay(confirmOrderVODataResponse.getData());
                    else if (payTypeEnum == PayTypeEnum.WXPAY_APP) {
                        PayDao.getInstance().setActivity(activity).goWeiXinPay(confirmOrderVODataResponse.getData());
                    }
                } else if (confirmOrderVODataResponse.getCode() == Constant.TOKEN_CODE) {
                    EventBus.getDefault().post(new TokenEvent());
                } else {
                    EventBus.getDefault().post(new PayEvent().setSucceed(false).setMsg(confirmOrderVODataResponse.getMessage()));
                }
            }
        });
    }

    public static void resetLoginDate(String orderNo) {
        AppExecutors.runNetworkIO(new Runnable() {
            @Override
            public void run() {
                DataResponse<List<UserFeatureVO>> listDataResponse = HttpUtils.getInstance().getService(CommonApiService.class)
                        .userFeatures(new BaseDto());
                LoginVO loginData = CacheUtils.getLoginData();
                loginData.setUserFeatures(listDataResponse.getData());
                CacheUtils.setLoginData(loginData);
            }
        });

    }

    private static String getPaydesc(Context context) {
        StringBuffer buffer = new StringBuffer();
        if (PublicUtil.checkApkExist(context, "com.eg.android.AlipayGphone")) {
            buffer.append("有 支付宝；");
        } else {
            buffer.append("无 支付宝；");
        }
        if (PublicUtil.checkApkExist(context, "com.tencent.mm")) {
            buffer.append("有 微信。");
        } else {
            buffer.append("无 微信。");
        }
        return buffer.toString();
    }
}
