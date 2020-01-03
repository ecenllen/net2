package com.yydd.net.net;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * 网络请求拦截器
 * Created by cuiyan on 16/6/8 12:07.
 */
public class CommonInterceptor implements Interceptor {


    @Override
    public synchronized Response intercept(Chain chain) throws IOException {
        Charset charset = Charset.forName("utf-8");
        Request request = chain.request();
        Log.d("lhp", "url:" + request.url());
        Buffer buffer = new Buffer();
        request.body().writeTo(buffer);
//        MediaType contentType = request.body().contentType();
//        if (contentType != null) {
//            charset = contentType.charset(charset);
//        }
        String body = buffer.readString(charset);
        Log.d("lhp", "body:" + body);
        Request newRequest = request.newBuilder()
                .addHeader("Authorization", "Bearer " + CacheUtils.getToken())
                .build();
        Response response = chain.proceed(newRequest);
        // 输出返回结果
        try {
//            Log.d("lhp", "response content-type:" + response.header("Content-Type"));
            for (String key :response.headers().names()) {
                Log.d("lhp","response header:"+key+"="+response.header(key));
            }
            if ("application/octet-stream".equals(response.header("Content-Type"))) {
                Log.d("lhp", "response file: "+response.header("Content-Disposition"));
            }else{
                ResponseBody responseBody = response.peekBody(Long.MAX_VALUE);
                Reader jsonReader = new InputStreamReader(responseBody.byteStream(), charset);
                BufferedReader reader = new BufferedReader(jsonReader);
                StringBuilder sbJson = new StringBuilder();
                String line = reader.readLine();
                do {
                    sbJson.append(line);
                    line = reader.readLine();
                } while (line != null);
                Log.d("lhp", "response: " + sbJson.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("lhp", e.getMessage(), e);
        }
        return response;
    }


}