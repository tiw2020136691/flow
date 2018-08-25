package com.bwie.tianwang20180825.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public interface RequestCallBack {
    void failure(Call call, IOException e);

    void onResponse(Call call, Response response);
}
