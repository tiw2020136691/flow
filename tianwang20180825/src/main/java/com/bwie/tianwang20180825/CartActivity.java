package com.bwie.tianwang20180825;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.tianwang20180825.adapter.CartAdapter;
import com.bwie.tianwang20180825.api.Api;
import com.bwie.tianwang20180825.bean.CartBean;
import com.bwie.tianwang20180825.presenter.CartPresenter;
import com.bwie.tianwang20180825.view.IcartView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity implements IcartView {

    private XRecyclerView xRecyclerView;
    private CheckBox allcheckBox;
    private TextView totalpriceTv;
    private CartPresenter cartPresenter;
    private List<CartBean.DataBean> list;
    private CartAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initData();
        initView();
    }

    private void initView() {
        allcheckBox = findViewById(R.id.allCheckBox);
        xRecyclerView = findViewById(R.id.cartRV);
        totalpriceTv = findViewById(R.id.totalpriceTv);
        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
        allcheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allcheckBox.isChecked()) {
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(true);
                            for (int j = 0; j < list.get(i).getList().size(); j++) {
                                list.get(i).getList().get(j).setSelected(true);
                            }
                        }
                    }
                } else {
                    if (list != null && list.size() > 0) {
                        for (int i = 0; i < list.size(); i++) {
                            list.get(i).setSelected(false);
                            for (int j = 0; j < list.get(i).getList().size(); j++) {
                                list.get(i).getList().get(j).setSelected(false);
                            }
                        }
                    }
                }
                mAdapter.notifyDataSetChanged();
                totalPrice();
            }
        });

    }

    private void totalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < mAdapter.getCartlist().size(); i++) {
            for (int j = 0; j < mAdapter.getCartlist().get(i).getList().size(); j++) {
                if (mAdapter.getCartlist().get(i).getList().get(j).isSelected()) {
                    CartBean.DataBean.ListBean listBean = mAdapter.getCartlist().get(i).getList().get(j);
                    totalPrice += listBean.getBargainPrice() * listBean.getTitaLnum();
                }

            }
        }
        totalpriceTv.setText("总价" + totalPrice);
    }

    private void initData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", "71");
        cartPresenter = new CartPresenter(this);
        cartPresenter.getCart(Api.CARTURL, params);

    }

    @Override
    public void success(CartBean cartBean) {
        list = cartBean.getData();
        mAdapter = new CartAdapter(this, list);
        xRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void failure(String msg) {

    }
}
