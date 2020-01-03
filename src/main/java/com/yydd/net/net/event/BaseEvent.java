package com.yydd.net.net.event;

public class BaseEvent {
    private boolean succeed;
    private String msg;
    private int code;

    public boolean isSucceed() {
        return succeed;
    }

    public BaseEvent setSucceed(boolean succeed) {
        this.succeed = succeed;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseEvent setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public BaseEvent setCode(int code) {
        this.code = code;
        return this;
    }
}
