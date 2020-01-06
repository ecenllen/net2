package com.yydd.net.net.InterfaceManager;

import android.text.TextUtils;

import com.yydd.net.net.AppExecutors;
import com.yydd.net.net.BaseDto;
import com.yydd.net.net.CacheUtils;
import com.yydd.net.net.DataResponse;
import com.yydd.net.net.HttpUtils;
import com.yydd.net.net.NetApplication;
import com.yydd.net.net.common.CommonApiService;
import com.yydd.net.net.constants.SysConfigEnum;
import com.yydd.net.net.event.ConfigEvent;
import com.yydd.net.net.help.ADHelp;
import com.yydd.net.net.util.HttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ADInterface {

    public static void getConfigs() {
        AppExecutors.runNetworkIO(() -> {
            DataResponse<Map<String, String>> configs = HttpUtils.getInstance().getService(CommonApiService.class).configs(new BaseDto());
            ConfigEvent configEvent = new ConfigEvent();
            configEvent.setSuccess(configs.success());
            if (configs.success()) {
                Map<String, String> data = configs.getData();
                initQhbsourceVersion(data); // 先执行这个再执行setConfigs(), 和下面setConfigs 顺序不能反
                initSelfadJson(data);
                initVideoJson(data);
                initZixunJson(data);
                initWXGZHJson(data);
                ADHelp.InitLocal();
                CacheUtils.setConfigs(data);
            } else {
                configEvent.setMsg(configs.getMessage());
            }
            EventBus.getDefault().post(configEvent);
        });
    }

    /**
     * 初始化抢红包JAR包
     */
    public static void initQhbsourceVersion(Map<String, String> data) {
        boolean isNeedUpdate = data != null && !TextUtils.isEmpty(data.get(SysConfigEnum.JAR_VERSION.getKeyName()))
                && !data.get(SysConfigEnum.JAR_VERSION.getKeyName()).equals(CacheUtils.getConfig(SysConfigEnum.JAR_VERSION));
        String qhblibPath = CacheUtils.getConfig(SysConfigEnum.QHB_SAVE_PATH);
        if (isNeedUpdate || (!(new File(qhblibPath).exists()) && data != null
                && !TextUtils.isEmpty(data.get(SysConfigEnum.JAR_VERSION.getKeyName())))) {//需要更新videosourceVersion 或者没有在目录下找到该jar,但是获取
            boolean isSuccess = true;
            try {
                HttpUtil.downloadjar(data.get(SysConfigEnum.JAR_VERSION_URL.getKeyName()), qhblibPath);
            } catch (Exception e1) {
                try {
                    HttpUtil.downloadjar(data.get(SysConfigEnum.JAR_VERSION_URL.getKeyName()), qhblibPath);
                } catch (Exception e2) {
                    try {
                        HttpUtil.downloadjar(data.get(SysConfigEnum.JAR_VERSION_URL.getKeyName()), qhblibPath);
                    } catch (Exception e3) {//这一步则表示下载失败
                        isSuccess = false;
                    }
                }
            }
            if (isSuccess) {
                CacheUtils.save(SysConfigEnum.JAR_VERSION.getKeyName(), data.get(SysConfigEnum.JAR_VERSION.getKeyName()));
            } else {
                HttpUtil.deleteFile(qhblibPath);
                CacheUtils.save(SysConfigEnum.JAR_VERSION.getKeyName(), "");
            }
        }
    }

    public static void initSelfadJson(Map<String, String> data) {
        if (data == null) return;
        String selfAdVersion = data.get(SysConfigEnum.AD_SELFAD_VERSION.getKeyName());
        if (!TextUtils.isEmpty(selfAdVersion) && !selfAdVersion.equals(CacheUtils.getConfig(SysConfigEnum.AD_SELFAD_VERSION))) {
            try {
                String url = data.get(SysConfigEnum.AD_SELFAD_URL.getKeyName());
                if(!TextUtils.isEmpty(url)) {
                    String selfAdJson = HttpUtil.getJson(url);
                    if (!TextUtils.isEmpty(selfAdJson)) {
                        CacheUtils.save(SysConfigEnum.AD_SELFAD_JSON.getKeyName(), selfAdJson);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void initZixunJson(Map<String, String> data) {
        if (data == null) return;
        String adVersion = data.get(SysConfigEnum.AD_ZIXUN_VERSION.getKeyName());
        if (!TextUtils.isEmpty(adVersion)) {
            if (!adVersion.equals(CacheUtils.getConfig(SysConfigEnum.AD_ZIXUN_VERSION))) {
                try {
                    String url = data.get(SysConfigEnum.AD_ZIXUN_URL.getKeyName());
                    String adJson = "";
                    if (!TextUtils.isEmpty(url)) {
                        adJson = HttpUtil.getJson(url);
                        if (!TextUtils.isEmpty(adJson)) {
                            CacheUtils.save(SysConfigEnum.AD_ZIXUN_JSON.getKeyName(), adJson);
                            CacheUtils.save(SysConfigEnum.AD_ZIXUN_VERSION.getKeyName(), adVersion);
                        } else {
                            adJson = getZixunJsonFromAssets("zixun.json");
                            if (!TextUtils.isEmpty(adJson))
                                CacheUtils.save(SysConfigEnum.AD_ZIXUN_JSON.getKeyName(), adJson);
                        }
                    } else {
                        adJson = getZixunJsonFromAssets("zixun.json");
                        if (!TextUtils.isEmpty(adJson))
                            CacheUtils.save(SysConfigEnum.AD_ZIXUN_JSON.getKeyName(), adJson);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (TextUtils.isEmpty(CacheUtils.getConfig(SysConfigEnum.AD_ZIXUN_JSON))) {
                String adJson = getZixunJsonFromAssets("zixun.json");
                if (!TextUtils.isEmpty(adJson))
                    CacheUtils.save(SysConfigEnum.AD_ZIXUN_JSON.getKeyName(), adJson);
            }
        }
    }

    public static void initVideoJson(Map<String, String> data) {
        if (data == null) return;
        String adVersion = data.get(SysConfigEnum.AD_VIDEO_SOURCE_VERSION.getKeyName());
        if (!TextUtils.isEmpty(adVersion)) {
            if (!adVersion.equals(CacheUtils.getConfig(SysConfigEnum.AD_VIDEO_SOURCE_VERSION))) {
                try {
                    String url = data.get(SysConfigEnum.AD_VIDEO_SOURCE_URL.getKeyName());
                    if (!TextUtils.isEmpty(url)) {
                        String adJson = HttpUtil.getJson(url);
                        if (!TextUtils.isEmpty(adJson)) {
                            CacheUtils.save(SysConfigEnum.AD_VIDEO_JSON.getKeyName(), adJson);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void initWXGZHJson(Map<String, String> data) {
        if (data == null) return;
        String adVersion = data.get(SysConfigEnum.AD_WXGZH_VERSION.getKeyName());
        if (!TextUtils.isEmpty(adVersion)) {
            if (!adVersion.equals(CacheUtils.getConfig(SysConfigEnum.AD_WXGZH_VERSION))) {
                try {
                    String url = data.get(SysConfigEnum.AD_WXGZH_URL.getKeyName());
                    if (!TextUtils.isEmpty(url)) {
                        String adJson = HttpUtil.getJson(url);
                        if (!TextUtils.isEmpty(adJson)) {
                            CacheUtils.save(SysConfigEnum.AD_WXGZH_JSON.getKeyName(), adJson);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取asset里的文件
     *
     * @param filename 文件名
     * @return
     */
    public static String getZixunJsonFromAssets(String filename) {
        String string = "";
        try {
            InputStream in = NetApplication.appContext.getResources().getAssets().open(filename);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            string = new String(buffer);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

}
