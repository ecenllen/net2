package com.yydd.net.net.constants;

public enum SysConfigEnum {
    KEFU_QQ("kefuqq", "2135125", "客服qq"),
    APP_DOWNLOAD_URL("app_download_url", "2135125", "app下载地址"),
    NOVIP_MAX_WORDS("novip_max_words", "30", "非VIP最大可转的字数"),
    NOVIP_DAY_COUNT("novip_day_count", "2", "非VIP每日可免费转换的次数"),
    UNLOCK_MUSIC_PRICE("unlock_music_price", "50", "解锁背景音乐所需金币"),
    WORK_TIME("worktime", "9:00-17:30", "工作时间"),
    IS_CHARGE("ischarge", "true", "工作时间"),
    SYSTEM_AUTO_LOGIN("ischarge", "false", "工作时间"),
    DISABLE_ALIPAY("disableAlipay", "false", "工作时间"),
    WX_APPID("wxappId", "", "工作时间"),


    AD_SHOW_HAOPING("ad_show_haoping", "false", "是否展示好评"),
    AD_SHOW_BANNER("ad_show_banner", "true", "是否展示banner广告"),
    AD_SHOW_KP("ad_show_kp", "true", "是否展示开屏广告"),
    AD_SHOW_CP("ad_show_cp", "true", "是否展示插屏广告"),
    AD_SHOW_TP("ad_show_tp", "true", "是否展示退屏广告"),
    AD_SHOW_SELFAD("ad_show_selfad", "true", "是否展示自己的广告"),
    AD_SHOW_VIDEO("ad_show_video", "true", "是否展示视频"),
    AD_SHOW_GZH("ad_show_gzh", "true", "是否展示公众号"),
    AD_SHOW_UPDATE("ad_show_update", "false", "是否展示更新"),
    AD_FENXIANG("ad_fenxiang", "false", "是否可以分享"),


    AD_BANNER_TYPE("ad_banner_type", "gdt2", "banner广告类型"),
    AD_BANNER_ID("ad_banner_id", "", "banner广告id"),
    AD_KP_TYPE("ad_kp_type", "gdt", "开屏广告类型"),
    AD_KP_ID("ad_kp_id", "", "开屏广告id"),
    AD_CP_TYPE("ad_cp_type", "gdt2", "插屏广告类型"),
    AD_CP_ID("ad_cp_id", "", "插屏广告id"),
    AD_TP_TYPE("ad_tp_type", "gdtmb", "退屏广告类型"),
    AD_TP_ID("ad_tp_id", "", "退屏广告ID"),

    AD_ZIXUN_VERSION("ad_zixun_version", "20191230", "新闻资讯配置版本号"),
    AD_ZIXUN_URL("ad_zixun_url", "json", "新闻资讯配置下载地址"),
    AD_ZIXUN_JSON("ad_zixun_json", "json", "新闻资讯配置保存key"),
    AD_VIDEO_SOURCE_VERSION("ad_video_source_version", "1", "视频源配置版本号"),
    AD_VIDEO_SOURCE_URL("ad_video_source_url", "json", "新闻资讯配置下载地址"),
    AD_VIDEO_JSON("ad_video_json", "json", "新闻资讯配置保存key"),
    AD_SELFAD_VERSION("ad_selfad_version", "", "新闻资讯配置下载地址"),
    AD_SELFAD_URL("ad_selfad_url", "json", "自己广告配置下载地址"),
    AD_SELFAD_JSON("ad_selfad_json", "", "自己广告配置保存key"),

    AD_WXGZH_VERSION("ad_wxgzh_version", "1", "微信公众号配置版本号"),
    AD_WXGZH_URL("ad_wxgzh_url", "json", "微信公众号配置下载地址"),
    AD_WXGZH_JSON("ad_wxgzh_json", "", "微信公众号配置保存key"),
    AD_ONLINE_VIDEO_PARSE_VERSION("ad_online_video_parse_version", "1", "视频解析库版本号"),
    AD_ONLINE_VIDEO_PARSE_URL("ad_online_video_parse_url", "json", "视频解析库下载地址"),
    AD_TV_VERSION("ad_tvversion", "json", "TV版本号"),
    AD_TV_URL("ad_tvurl", "json", "TV下载地址"),

    UPDATE_MSG("updatemsg_msg", "版本更新信息", "版本更新信息"),
    UPDATE_PACKAGENAME("updatemsg_packagename", "版本更新信息", "版本更新信息包名"),
    UPDATE_VERSION("updatemsg_version_code", "1", "更新版本号"),
    UPDATE_URL("updatemsg_url", "url", "版本更新下载地址"),


    QQ_KEY("qqkey", "", "qqKey"),
    INFORMATION("information", "", "qqKey"),
    FENXIANG_INFO("fenxiang_info", "分享信息", "分享信息"),
    JAR_VERSION("jar_version", "1", "jar库版本号"),
    JAR_VERSION_URL("jar_download_url", "1", "jar库下载地址"),
    JAR_VERSION2("jar_version2", "1", "jar库2版本号"),
    JAR_VERSION_URL2("jar_download_url2", "1", "jar库2下载地址"),


    QHB_SAVE_PATH("qhb_save_path", "", "抢红包jar保存地址"),
    GZH_SAVE_PATH("gzh_save_path", "", "公众号jar保存地址"),


    ;


    private String keyName;
    private String value;
    private String desc;

    SysConfigEnum(String keyName, String value, String desc) {
        this.keyName = keyName;
        this.value = value;
        this.desc = desc;
    }

    public String getKeyName() {
        return keyName;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public int getValueInt() {
        return Integer.valueOf(getValue());
    }

    public float getValueFloat() {
        return Float.valueOf(getValue());
    }

    public boolean getValueBoolean() {
        return Boolean.valueOf(getValue());
    }

}