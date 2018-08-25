package com.bwie.tianwang20180825.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.tianwang20180825.R;
import com.bwie.tianwang20180825.bean.CartBean;
import com.bwie.tianwang20180825.view.CartAllCheckboxlistener;
import com.bwie.tianwang20180825.view.CheckListener;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> implements CheckListener {

    Context mContext;
    List<CartBean.DataBean> cartList;
    CheckListener checkListener;
    CartAllCheckboxlistener cartAllCheckboxlistener;

    public void setCartAllCheckboxlistener(CartAllCheckboxlistener cartAllCheckboxlistener) {
        this.cartAllCheckboxlistener = cartAllCheckboxlistener;
    }

    public CartAdapter(Context context, List<CartBean.DataBean> List) {
        this.mContext = context;
        this.cartList = List;
    }

    public void addPageData(List<CartBean.DataBean> list) {
        if (cartList != null) {
            cartList.addAll(list);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item1_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.ViewHolder holder, int position) {
        final CartBean.DataBean dataBean = cartList.get(position);
        holder.checkBox.setChecked(dataBean.isSelected());
        holder.textView.setText(dataBean.getSellerName());

        holder.productRV.setLayoutManager(new LinearLayoutManager(mContext));
        ProductAdapter productAdapter = new ProductAdapter(mContext, dataBean.getList());
        holder.productRV.setAdapter(productAdapter);
        for (int i = 0; i < dataBean.getList().size(); i++) {
            if (!dataBean.getList().get(i).isSelected()) {
                holder.checkBox.setChecked(false);
                break;
            } else {
                holder.checkBox.setChecked(true);
            }
        }

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    dataBean.setSelected(true);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelected(true);
                    }
                } else {
                    dataBean.setSelected(false);
                    for (int i = 0; i < dataBean.getList().size(); i++) {
                        dataBean.getList().get(i).setSelected(false);
                    }
                }
                notifyDataSetChanged();

                if (cartAllCheckboxlistener != null) {
                    cartAllCheckboxlistener.notifyAllCheckboxStatus();
                }
            }
        });
    }

    public List<CartBean.DataBean> getCartlist() {
        return cartList;
    }

    @Override
    public int getItemCount() {
        return cartList.size() == 0 ? 0 : cartList.size();
    }

    @Override
    public void notifyParent() {
        notifyDataSetChanged();
        if (cartAllCheckboxlistener != null) {
            cartAllCheckboxlistener.notifyAllCheckboxStatus();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView;
        RecyclerView productRV;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.sellerCheckBox);
            textView = itemView.findViewById(R.id.sellerNameTv);
            productRV = itemView.findViewById(R.id.productRV);
        }
    }
}
