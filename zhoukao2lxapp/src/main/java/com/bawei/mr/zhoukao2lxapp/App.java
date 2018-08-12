package com.bawei.mr.zhoukao2lxapp;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
