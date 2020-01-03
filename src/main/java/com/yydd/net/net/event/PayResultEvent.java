package com.yydd.net.net.event;

/**
 * Created by yingyongduoduo on 2019/6/29.
 */

public class PayResultEvent {

    private boolean success;
    private String result;

    public boolean isSuccess() {
        return success;
    }

    public PayResultEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getResult() {
        return result;
    }

    public PayResultEvent setResult(String result) {
        this.result = result;
        return this;
    }
}
