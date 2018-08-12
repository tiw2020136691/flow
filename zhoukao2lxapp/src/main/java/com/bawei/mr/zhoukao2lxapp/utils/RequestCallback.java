package com.bawei.mr.zhoukao2lxapp.utils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public interface RequestCallback {

    void failure(Call call, IOException e);
    void onResponse(Call call, Response response);
}
