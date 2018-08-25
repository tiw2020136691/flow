package com.bwie.tianwang20180825.model;

import android.os.Handler;
import android.text.TextUtils;

import com.bwie.tianwang20180825.bean.CartBean;
import com.bwie.tianwang20180825.utils.OkHttpUtils;
import com.bwie.tianwang20180825.utils.RequestCallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class CartModel {
    Handler handler = new Handler();

    public void getCart(String url, HashMap<String, String> params, final CartCallBack cartCallBack) {
        OkHttpUtils.getInstace().postData(url, params, new RequestCallBack() {
            @Override
            public void failure(Call call, IOException e) {
                if (cartCallBack != null) {
                    cartCallBack.fail("网络异常");
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String string = response.body().string();
                    if (!TextUtils.isEmpty(string)) {
                        paraseJson(string, cartCallBack);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void paraseJson(String string, final CartCallBack cartCallBack) {
        final CartBean cartBean = new Gson().fromJson(string, CartBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                cartCallBack.success(cartBean);
            }
        });
    }

    public interface CartCallBack {
        void success(CartBean cartBean);

        void fail(String msg);
    }
}
