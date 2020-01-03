package com.yydd.net.net;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yydd.net.net.event.PayEvent;
import com.yydd.net.net.event.PayResult;
import com.yydd.net.net.common.vo.ConfirmOrderVO;
import com.yydd.net.net.constants.Constant;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


/**
 * Created by shanlin on 2017/10/7.
 */

public class PayDao {

    private final Executor executor = Executors.newCachedThreadPool();

    private volatile Activity activity;

    private volatile IWXAPI api;

    private PayDao() {
    }

    private static PayDao payDao;

    public static PayDao getInstance() {
        if (null == payDao) {
            synchronized (PayDao.class) {
                if (null == payDao) {
                    payDao = new PayDao();
                }
            }
        }
        return payDao;
    }

    public PayDao setActivity(Activity activity) {
        this.activity = activity;
        return this;
    }

    public PayDao setApi(IWXAPI api) {
        this.api = api;
        return this;
    }


    /*
    9000	订单支付成功
    8000	正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    4000	订单支付失败
    5000	重复请求
    6001	用户中途取消
    6002	网络连接出错
    6004	支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    其它	其它支付错误
     */
    public void goAlipay(final ConfirmOrderVO orderInfo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(activity);
                Map<String, String> result = alipay.payV2(orderInfo.getPaymentData(), true);
                Log.i("msp", result.toString());
                PayResult payResult = new PayResult(result);
                /**
                 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                final String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                final String resultStatus = payResult.getResultStatus();
                /**
                 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                 */
                // 判断resultStatus 为9000则代表支付成功
                PayEvent payEvent = new PayEvent();
                if (TextUtils.equals(resultStatus, "4000")) {
                    payEvent.setSucceed(false).setMsg("订单支付失败");
                } else if (TextUtils.equals(resultStatus, "5000")) {
                    payEvent.setSucceed(false).setMsg("重复请求");
                } else if (TextUtils.equals(resultStatus, "6001")) {
                    payEvent.setSucceed(false).setMsg("用户取消");
                } else if (TextUtils.equals(resultStatus, "6002")) {
                    payEvent.setSucceed(false).setMsg("网络错误");
                } else if (TextUtils.equals(resultStatus, "其它")) {
                    payEvent.setSucceed(false).setMsg("其它支付错误");
                } else {
                    // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
//                    getOrderStatus(payResult.getResult().getOrderNo());
//                            notifyPaySuccess();
                    payEvent.setSucceed(true).setOrderNo(orderInfo.getOrderNo());
                }
                EventBus.getDefault().post(payEvent);
            }
        });
    }


    public void goWeiXinPay(final ConfirmOrderVO content) {
        Log.e("goWeiXinPay", content + "");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                String url = "https://wxpay.wxutil.com/pub_v2/app/app_pay.php"; // 官方提供的测试接口
                if (activity == null) return;
                if (api == null) {
                    String wxappId = CacheUtils.getLoginData().getConfig(Constant.WXAPPID_KEY, "");
                    api = WXAPIFactory.createWXAPI(activity, wxappId);
                    api.registerApp(wxappId);
                }
                boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                if (!isPaySupported) {
                    if (activity == null) return;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(activity, "没有安装微信！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    return;
                }
                try {
                    if (content != null && !TextUtils.isEmpty(content.getPaymentData())) {
                        JSONObject json = new JSONObject(content.getPaymentData());
                        if (!json.has("retcode")) {
                            PayReq req = new PayReq();
                            req.appId = json.optString("appId");
                            req.partnerId = json.optString("partnerId");
                            req.prepayId = json.optString("prepayId");
                            req.nonceStr = json.optString("nonceStr");
                            req.timeStamp = json.optString("timeStamp");
                            req.packageValue = json.optString("packageValue");
                            String sign = json.optString("sign");
                            req.sign = sign;
                            req.extData = content.getOrderNo(); // optional
//                            Toast.makeText(PayActivity.this, "正常调起支付", Toast.LENGTH_SHORT).show();
                            // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                            boolean b = api.sendReq(req);
                            if (!b) {
                                if (activity == null) return;
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            if (activity == null) return;
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(activity, "返回数据错误！", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        if (activity == null) return;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(activity, "服务器请求错误！", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("PAY_GET", "异常：" + e.getMessage());
                }

            }
        });
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(api != null)
            api.unregisterApp();
    }
}
