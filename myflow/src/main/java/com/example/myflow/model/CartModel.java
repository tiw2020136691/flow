package com.example.myflow.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.myflow.bean.CartBean;
import com.example.myflow.utils.OkHttpUtils;
import com.example.myflow.utils.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class CartModel {

    Handler handler = new Handler();

    public void getCarts(String url, HashMap<String, String> params, final CartCallback cartCallback) {

        OkHttpUtils.getInstance().postData(url, params, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {
                if (cartCallback != null) {
                    cartCallback.fail("请示失败");
                }
            }

            @Override
            public void onResponse(Call call, Response response) {
                try {
                    String string = response.body().string();
                    if (!TextUtils.isEmpty(string)) {
                        parseResult(string, cartCallback);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void parseResult(String string, final CartCallback cartCallback) {
        final CartBean cartBean = new Gson().fromJson(string, CartBean.class);
        if (cartCallback != null && cartCallback != null) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    cartCallback.success(cartBean);
                }
            });
        }
    }

    public interface CartCallback {
        void success(CartBean cartBean);

        void fail(String msg);
    }
}
