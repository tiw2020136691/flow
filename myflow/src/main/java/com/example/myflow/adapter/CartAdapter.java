package com.example.myflow.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.myflow.R;
import com.example.myflow.bean.CartBean;
import com.example.myflow.view.CartAllCheckboxListener;
import com.example.myflow.view.CartCheckListener;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> implements CartCheckListener {
    Context mContext;
    List<CartBean.DataBean> cartList;
    CartAllCheckboxListener allCheckboxListener;

    public CartAdapter(Context Context, List<CartBean.DataBean> list) {
        this.mContext = Context;
        this.cartList = list;
    }

    //暴露给购物车页面进行回调
    public void setAllCheckboxListener(CartAllCheckboxListener allCheckboxListener) {
        this.allCheckboxListener = allCheckboxListener;
    }

    public void addPageData(List<CartBean.DataBean> list) {
        if (cartList != null) {
            cartList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public CartAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.cart_item_layout, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(itemView);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.CartViewHolder holder, int position) {
        final CartBean.DataBean bean = cartList.get(position);
        holder.nameTv.setText(bean.getSellerName());
        holder.checkBox.setChecked(bean.isSelected());

        holder.productXRV.setLayoutManager(new LinearLayoutManager(mContext));
        ProductAdapter productAdapter = new ProductAdapter(mContext, bean.getList());
        holder.productXRV.setAdapter(productAdapter);
        productAdapter.setCartCheckListener(this);

        for (int i = 0; i < bean.getList().size(); i++) {
            if (!bean.getList().get(i).isSelected()) {
                holder.checkBox.setChecked(false);
                break;
            } else {
                holder.checkBox.setChecked(true);
            }
        }

        //设置商家的CheckBox点击事件
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    bean.setSelected(true);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setSelected(true);
                    }
                } else {
                    bean.setSelected(false);
                    for (int i = 0; i < bean.getList().size(); i++) {
                        bean.getList().get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();

                if (allCheckboxListener != null) {
                    allCheckboxListener.notifyAllCheckboxStatus();
                }
            }
        });

    }

    /**
     * 暴露修改后的最新的集合数据
     *
     * @return
     */
    public List<CartBean.DataBean> getCartList() {
        return cartList;
    }

    @Override
    public int getItemCount() {
        return cartList.size() == 0 ? 0 : cartList.size();
    }

    /**
     * 刷新适配器的回调
     */
    @Override
    public void notifyParent() {
        notifyDataSetChanged();
        if (allCheckboxListener != null) {
            allCheckboxListener.notifyAllCheckboxStatus();
        }
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        private TextView nameTv;
        private RecyclerView productXRV;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.sellerCheckbox);
            nameTv = itemView.findViewById(R.id.sellerNameTv);
            productXRV = itemView.findViewById(R.id.productXRV);

        }
    }
}
