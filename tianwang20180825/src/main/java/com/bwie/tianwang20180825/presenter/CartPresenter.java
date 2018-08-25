package com.bwie.tianwang20180825.presenter;

import com.bwie.tianwang20180825.bean.CartBean;
import com.bwie.tianwang20180825.model.CartModel;
import com.bwie.tianwang20180825.view.IcartView;

import java.util.HashMap;

public class CartPresenter {

    private CartModel cartModel;
    private IcartView icartView;

    public CartPresenter(IcartView icartView) {
        cartModel = new CartModel();
        this.icartView = icartView;
    }

    public void getCart(String url, HashMap<String, String> params) {
        cartModel.getCart(url, params, new CartModel.CartCallBack() {
            @Override
            public void success(CartBean cartBean) {
                icartView.success(cartBean);
            }

            @Override
            public void fail(String msg) {
                icartView.failure(msg);
            }
        });
    }
}
