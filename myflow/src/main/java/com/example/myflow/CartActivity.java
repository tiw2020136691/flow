package com.example.myflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflow.adapter.CartAdapter;
import com.example.myflow.bean.CartBean;
import com.example.myflow.common.Api;
import com.example.myflow.presenter.CartPresenter;
import com.example.myflow.view.CartAllCheckboxListener;
import com.example.myflow.view.ICartview;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CartActivity extends AppCompatActivity implements ICartview, CartAllCheckboxListener {

    private XRecyclerView xRecyclerView;
    private CheckBox allCheckbox;
    private TextView totalPriceTv;
    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;
    private List<CartBean.DataBean> list;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        initData();
    }

    private void initView() {
        list = new ArrayList<>();
        xRecyclerView = findViewById(R.id.cartGV);
        allCheckbox = findViewById(R.id.allCheckbox);
        totalPriceTv = findViewById(R.id.totalpriceTv);

        xRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //支持加载更多
        xRecyclerView.setLoadingMoreEnabled(true);
        xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉刷新
            @Override
            public void onRefresh() {
                page = 1;
                loadData();
            }

            //上拉加载
            @Override
            public void onLoadMore() {
                page++;
                loadData();
            }
        });

        /**
         * 全选按钮
         */
        allCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allCheckbox.isChecked()) {
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
                //全部刷新
                cartAdapter.notifyDataSetChanged();
//                totalprice();
            }
        });

    }

    private void initData() {
        loadData();
    }

    /**
     * 请求数据
     */
    private void loadData() {

        HashMap<String, String> params = new HashMap<>();
        params.put("uid", "71");

        cartPresenter = new CartPresenter(this);
        cartPresenter.getCarts(params, Api.GETCARTS);
    }

    /**
     * 去结算
     *
     * @param view
     */
    public void buy(View view) {

    }

    /**
     * 刷新购物车列表
     *
     * @param cartBean
     */
    @Override
    public void success(CartBean cartBean) {
        //展示列表数据
        if (cartBean != null && cartBean.getData() != null) {
            if (page == 1) {
                list = cartBean.getData();
                cartAdapter = new CartAdapter(this, list);
                xRecyclerView.setAdapter(cartAdapter);
                xRecyclerView.refreshComplete();
            } else {
                if (cartAdapter != null) {
                    cartAdapter.addPageData(cartBean.getData());
                }
                xRecyclerView.loadMoreComplete();
            }
//            cartAdapter.setCartAllCheckboxListener(this);
        }
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 全选按钮是否选中的回调
     */
    @Override
    public void notifyAllCheckboxStatus() {
        StringBuilder stringBuilder = new StringBuilder();
        if (cartAdapter != null) {
            for (int i = 0; i < cartAdapter.getCartList().size(); i++) {
                stringBuilder.append(cartAdapter.getCartList().get(i).isSelected());
                for (int j = 0; j < cartAdapter.getCartList().get(i).getList().size(); j++) {
                    stringBuilder.append(cartAdapter.getCartList().get(j).getList().get(j).isSelected());
                }
            }
        }
        if (stringBuilder.toString().contains("false")) {
            allCheckbox.setChecked(false);
        } else {
            allCheckbox.setChecked(true);
        }
    }
}
