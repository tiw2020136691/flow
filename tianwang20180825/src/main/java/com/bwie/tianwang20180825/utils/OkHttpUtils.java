package com.bwie.tianwang20180825.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {

    private OkHttpClient okHttpClient;
    private static OkHttpUtils okHttpUtils;

    public OkHttpUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

    public static OkHttpUtils getInstace() {
        if (okHttpUtils == null) {
            synchronized (OkHttpUtils.class) {
                if (okHttpUtils == null) {
                    okHttpUtils = new OkHttpUtils();
                }
            }
        }
        return okHttpUtils;
    }

    public void getData(String url, HashMap<String, String> params, final RequestCallBack requestCallBack) {
        StringBuilder urlsb = new StringBuilder();
        String allUrl = "";
        for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {

            urlsb.append("?").append(stringStringEntry.getKey()).append("=").append(stringStringEntry.getValue()).append("&");
        }
        allUrl = url + urlsb.toString().substring(0, urlsb.length() - 1);

        Request request = new Request.Builder()
                .url(allUrl)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallBack != null) {
                    requestCallBack.failure(call, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallBack != null) {
                    requestCallBack.onResponse(call, response);
                }
            }
        });
    }

    public void postData(String url, HashMap<String, String> params, final RequestCallBack requestCallBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.size() >= 0) {
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
                if (requestCallBack != null) {
                    requestCallBack.failure(call, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallBack != null) {
                    requestCallBack.onResponse(call, response);
                }
            }
        });
    }

}
