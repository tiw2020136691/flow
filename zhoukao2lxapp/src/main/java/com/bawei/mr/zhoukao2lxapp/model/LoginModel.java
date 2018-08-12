package com.bawei.mr.zhoukao2lxapp.model;

import android.os.Handler;
import android.os.Message;

import com.bawei.mr.zhoukao2lxapp.api.Api;
import com.bawei.mr.zhoukao2lxapp.bean.LoginBean;
import com.bawei.mr.zhoukao2lxapp.utils.OkHttpUtils;
import com.bawei.mr.zhoukao2lxapp.utils.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public class LoginModel {
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public void login(HashMap<String,String> params, final LoginCallback loginCallback){
        OkHttpUtils.getInstance().postData(Api.LOG_URL, params, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {
                if (loginCallback != null){
                    loginCallback.failure("请求失败");
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200){
                    try {
                        String result = response.body().string();
                        parseJsonResult(result,loginCallback);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void parseJsonResult(String result, final LoginCallback loginCallback) {
        if (result !=null){
            final LoginBean loginBean = new Gson().fromJson(result, LoginBean.class);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (loginCallback != null){
                        loginCallback.LoginSuccess(loginBean);
                    }
                }
            });
        }
    }

    public interface LoginCallback{
        void failure(String errorMsg);
        void LoginSuccess(LoginBean loginBean);
    }
}
