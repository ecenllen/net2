package com.yydd.net.net;

public class DataResponse<T> {
    private int code;
    private String message;
    private T data;

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


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean success() {
        return code == 0;
    }

    public  static <E> DataResponse<E> success(E data) {
        DataResponse<E> dataResponse = new DataResponse<>();
        dataResponse.setCode(0);
        dataResponse.setData(data);
        return dataResponse;
    }

    public  static <E> DataResponse<E> fail(String message) {
        return fail(505,message);
    }
    public  static <E> DataResponse<E> fail(int code ,String message) {
        DataResponse<E> eDataResponse = new DataResponse<>();
        eDataResponse.code=code;
        eDataResponse.message=message;
        return eDataResponse;
    }

}
