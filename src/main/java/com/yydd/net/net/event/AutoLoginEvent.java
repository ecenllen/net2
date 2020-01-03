package com.yydd.net.net.event;

/**
 * Created by yingyongduoduo on 2019/7/1.
 */

public class AutoLoginEvent {
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public AutoLoginEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
