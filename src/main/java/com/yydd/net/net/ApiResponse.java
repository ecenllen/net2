package com.yydd.net.net;

public class ApiResponse {
    /**
     * 900 token 过期
     */
    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean success() {
        return code == 0;
    }

    public static ApiResponse ok(){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(0);
        return apiResponse;
    }
    public static ApiResponse fail(String message){
        return fail(505,message);
    }
    public static ApiResponse fail(int code,String message){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(code);
        apiResponse.setMessage(message);
        return apiResponse;
    }
}