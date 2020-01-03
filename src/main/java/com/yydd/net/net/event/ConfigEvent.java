package com.yydd.net.net.event;

public class ConfigEvent {
    private boolean success;
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public ConfigEvent setSuccess(boolean success) {
        this.success = success;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ConfigEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }
}
