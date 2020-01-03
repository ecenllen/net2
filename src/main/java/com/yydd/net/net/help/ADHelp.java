package com.yydd.net.net.help;

import android.text.TextUtils;

import com.yydd.net.net.CacheUtils;
import com.yydd.net.net.adbean.ADBean;
import com.yydd.net.net.adbean.VideoBean;
import com.yydd.net.net.adbean.WXGZHBean;
import com.yydd.net.net.adbean.ZiXunItemBean;
import com.yydd.net.net.constants.SysConfigEnum;
import com.yydd.net.net.util.ADBeanUtil;

import java.util.ArrayList;
import java.util.List;

public class ADHelp {
    public static List<VideoBean> videoBeans = new ArrayList<VideoBean>();
    public static List<ADBean> selfadBeans = new ArrayList<ADBean>();
    public static List<ZiXunItemBean> ziXunBeans = new ArrayList<>();
    public static List<WXGZHBean> wxgzhBeans = new ArrayList<WXGZHBean>();

    public static void InitLocal() {
        initVideoBean(CacheUtils.getConfig(SysConfigEnum.AD_VIDEO_JSON));
        initselfadBeans(CacheUtils.getConfig(SysConfigEnum.AD_SELFAD_JSON));
        initZixunBeans(CacheUtils.getConfig(SysConfigEnum.AD_ZIXUN_JSON));
        initwxgzhBeans(CacheUtils.getConfig(SysConfigEnum.AD_WXGZH_JSON));
    }

    private static void initVideoBean(String videoJson) {
        if (videoJson == null) return;
        try {
            List<VideoBean> currentVideoBeans = ADBeanUtil.getVideoBean(videoJson);
            if (currentVideoBeans.size() != 0) {
                videoBeans = currentVideoBeans;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void initselfadBeans(String selfJson) {
        if (TextUtils.isEmpty(selfJson)) return;
        try {
            List<ADBean> currentSelfAdBeans = ADBeanUtil.getSelfAdBeans(selfJson);
            if (currentSelfAdBeans != null && currentSelfAdBeans.size() != 0) {
                selfadBeans = currentSelfAdBeans;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initZixunBeans(String zixunJson) {
        if (zixunJson == null) return;
        try {
            List<ZiXunItemBean> currentSelfAdBeans = ADBeanUtil.getZiXunBeans(zixunJson);
            if (currentSelfAdBeans.size() != 0) {
                ziXunBeans = currentSelfAdBeans;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initwxgzhBeans(String wxgzhJson) {
        if (TextUtils.isEmpty(wxgzhJson)) return;
        try {
            List<WXGZHBean> currentSelfAdBeans = ADBeanUtil.getWXGZHBeans(wxgzhJson);
            if (currentSelfAdBeans.size() != 0) {
                wxgzhBeans = currentSelfAdBeans;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
