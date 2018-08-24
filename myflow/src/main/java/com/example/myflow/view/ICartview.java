package com.example.myflow.view;

import com.example.myflow.bean.CartBean;

public interface ICartview {
    void success(CartBean cartBean);//请求成功
    void failure(String msg);//请求失败
}
