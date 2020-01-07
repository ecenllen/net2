package com.yydd.net.net;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.yydd.net.net.util.PublicUtil;

import java.util.UUID;


public class BaseDto {
    public String agencyChannel = PublicUtil.metadata(NetApplication.appContext, "AGENCY_CHANNEL"); //代理渠道,NOT NULL
    public String appMarket = PublicUtil.metadata(NetApplication.appContext, "APP_MARKET"); //应用市场,NOT NULL
    public String appPackage = PublicUtil.getAppPackage(NetApplication.appContext);  //应用包名
    public String appName = PublicUtil.getAppName(NetApplication.appContext); //应用名称
    public String appVersion = PublicUtil.getAppInfo(NetApplication.appContext).versionName; //应用版本
    public int appVersionCode = PublicUtil.getVersionCode(NetApplication.appContext); //应用版本号
    public String deviceName = Build.MODEL;  //设备名称
    public String deviceBrand = Build.BRAND; //品牌
    public String deviceManufacturer = Build.MANUFACTURER; //设备制造商
    public String deviceFingerPrint = getUniqueId();// 设备的唯一标志
    public String devicePlatform = "ANDROID";   //设备平台
//    public String application = "RED_PACKET";   //对应项目(不同项目需要修改)
    public String application =  PublicUtil.metadata(NetApplication.appContext, "application");   //对应项目(不同项目需要修改)

    public static String getUniqueId() {
        String imeistring = null;
        String serialno = null;
        String androidId = null;

        TelephonyManager telephonyManager = (TelephonyManager) NetApplication.appContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (Build.VERSION.SDK_INT >= 29) {

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (NetApplication.appContext.checkSelfPermission(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    if (telephonyManager != null) {
                        imeistring = telephonyManager.getImei();
                    }
                    if (TextUtils.isEmpty(imeistring)) {
                        if (telephonyManager != null) {
                            imeistring = telephonyManager.getMeid();
                        }
                    }
                } else {
                    imeistring = telephonyManager.getDeviceId();
                }
            }
        } else {
            if (telephonyManager != null) {
                imeistring = telephonyManager.getDeviceId();
            }
        }
        if (!TextUtils.isEmpty(imeistring)) {
            return "imei/" + imeistring;
        }

        try {
            if (Build.VERSION.SDK_INT >= 29) {

            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serialno = Build.getSerial();
            } else {
                serialno = Build.SERIAL;
            }
            if (!TextUtils.isEmpty(serialno)) {
                return "serailno/" + serialno;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        androidId = Settings.Secure.getString(NetApplication.appContext.getContentResolver(), Settings.Secure.ANDROID_ID);
        if (!TextUtils.isEmpty(androidId) && !"9774d56d682e549c".equals(androidId)) {
            return "androidid/" + androidId;
        }

        return Installation.id(NetApplication.appContext);

    }

    public static String getUUID() {

        String serial = null;

        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serial = Build.getSerial();
            } else {
                serial = Build.SERIAL;
            }
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }
}