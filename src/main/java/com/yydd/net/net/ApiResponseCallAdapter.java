package com.yydd.net.net;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

public class ApiResponseCallAdapter<R> implements CallAdapter<R, ApiResponse> {


    @Override
    public Type responseType() {
        return ApiResponse.class;
    }

    @Override
    public ApiResponse adapt(Call<R> call) {
        Log.d("lhp","api call adapter");
        try {
            Response<R> response = call.execute();
            R body = response.body();
            if (body instanceof ApiResponse) {
                return ((ApiResponse) body);
            } else {
                return ApiResponse.fail("类型不正确");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.fail(100, e.getMessage());
        }
    }

}