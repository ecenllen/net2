package com.yydd.net.net.adbean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yingyongduoduo on 2019/2/27.
 */

public class ZiXunListItemBean implements Parcelable {
    private String name;
    private String url;

    public ZiXunListItemBean(){}

    protected ZiXunListItemBean(Parcel in) {
        name = in.readString();
        url = in.readString();
    }

    public String getName() {
        return name;
    }

    public ZiXunListItemBean setName(String name) {
        this.name = name;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public ZiXunListItemBean setUrl(String url) {
        this.url = url;
        return this;
    }

    public static final Creator<ZiXunListItemBean> CREATOR = new Creator<ZiXunListItemBean>() {
        @Override
        public ZiXunListItemBean createFromParcel(Parcel in) {
            return new ZiXunListItemBean(in);
        }

        @Override
        public ZiXunListItemBean[] newArray(int size) {
            return new ZiXunListItemBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
    }
}
