package com.yydd.net.net;

import android.util.Log;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class CommonCallAdapterFactory extends CallAdapter.Factory {
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Linq.of(annotations).forEach(annotation -> {

        });
        Class<?> rawType = getRawType(returnType);
        Log.d("lhp", "rawType:" + rawType.getName());
        if (returnType == ApiResponse.class) {
            return new ApiResponseCallAdapter<Object>();
        } else if (rawType == DataResponse.class) {
            return new DataResponseCallAdapter<>(returnType);
        }

        return null;
    }
}