package com.yydd.net.net;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

public class DataResponseCallAdapter<R,T> implements CallAdapter<R,DataResponse<T>> {
    private Type returnType;

    public DataResponseCallAdapter(Type returnType) {
        this.returnType =returnType;
    }

    @Override
    public Type responseType() {
        Log.d("lhp","dataResponse adapt, reponseType");
        return returnType;
    }

    @Override
    public DataResponse<T> adapt(Call<R> call) {
        Log.d("lhp","dataResponse adapt");
        try {
            Response<R> execute = call.execute();
            if(execute.isSuccessful()){
                Log.d("lhp","dataResponse adapt---success");
                R body = execute.body();
                if(body instanceof DataResponse){
                    Log.d("lhp","dataResponse adapt---right type");
                    return (DataResponse<T>)body;
                }else{
                    Log.d("lhp","dataResponse adapt---wrong type");
                    return DataResponse.fail(102,"类型不正确");
                }
            }else{
                Log.d("lhp","dataResponse adapt---fail");
                return DataResponse.fail(101,execute.errorBody().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return DataResponse.fail(100,e.getMessage());
        }
    }

}