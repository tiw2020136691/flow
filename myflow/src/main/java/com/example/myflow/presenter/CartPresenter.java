package com.example.myflow.presenter;

import com.example.myflow.bean.CartBean;
import com.example.myflow.model.CartModel;
import com.example.myflow.view.ICartview;

import java.util.HashMap;

public class CartPresenter {

    private CartModel cartModel;
    private ICartview iCartview;

    public CartPresenter(ICartview iCartview) {
        cartModel = new CartModel();
        attachView(iCartview);
    }

    private void attachView(ICartview iCartview) {
        this.iCartview = iCartview;
    }

    public void getCarts(HashMap<String, String> params, String url) {
        cartModel.getCarts(url, params, new CartModel.CartCallback() {
            @Override
            public void success(CartBean cartBean) {
                if (iCartview != null) {
                    iCartview.success(cartBean);
                }
            }

            @Override
            public void fail(String msg) {
                if (iCartview != null) {
                    iCartview.failure(msg);
                }
            }
        });
    }

    /**
     * 解除绑定，避免内存泄漏
     */
    public void detachView() {
        this.iCartview = null;
    }

}
