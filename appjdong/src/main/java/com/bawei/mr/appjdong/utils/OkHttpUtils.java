package com.bawei.mr.appjdong.utils;

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

/**
 * author:Created by WangZhiQiang on 2018/8/13.
 */
public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;

    private OkHttpUtils() {
        okHttpClient = new OkHttpClient.Builder()
                .writeTimeout(5000, TimeUnit.SECONDS)
                .readTimeout(5000,TimeUnit.SECONDS)
                .connectTimeout(5000,TimeUnit.SECONDS)
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

    public void postData(String url, HashMap<String, String> params, final RequestCallback requestCallback) {
        FormBody.Builder formBodyBuilder = new FormBody.Builder();

        if (params != null && params.size() > 0) {
            for (Map.Entry<String, String> stringStringEntry : params.entrySet()) {
                formBodyBuilder.add(stringStringEntry.getKey(), stringStringEntry.getValue());
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(formBodyBuilder.build())
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
