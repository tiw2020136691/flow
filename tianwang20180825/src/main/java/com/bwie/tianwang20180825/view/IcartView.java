package com.bwie.tianwang20180825.view;

import com.bwie.tianwang20180825.bean.CartBean;

public interface IcartView {
    void success(CartBean cartBean);

    void failure(String msg);
}
