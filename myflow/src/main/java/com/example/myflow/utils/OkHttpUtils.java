package com.example.myflow.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpUtils {

    private OkHttpClient okHttpClient;
    private static OkHttpUtils okHttpUtils;

    private OkHttpUtils() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtils getInstance() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }

        }
        return okHttpUtils;
    }

    public void getData(String url, HashMap<String, String> params, final RequestCallback requestCallback) {
        StringBuilder urlsb = new StringBuilder();
        String allUrl = "";
        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
            urlsb.append("?").append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("&");
        }
        allUrl = url + urlsb.toString().substring(0, urlsb.length() - 1);

        Request request = new Request.Builder()
                .get()
                .url(allUrl)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback != null) {
                    requestCallback.failure(call, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallback != null) {
                    requestCallback.onResponse(call, response);
                }
            }
        });
    }

    public void postData(String url, HashMap<String, String> params, final RequestCallback requestCallback) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                builder.add(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallback != null) {
                    requestCallback.failure(call, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallback != null) {
                    requestCallback.onResponse(call, response);
                }
            }
        });
    }
}
