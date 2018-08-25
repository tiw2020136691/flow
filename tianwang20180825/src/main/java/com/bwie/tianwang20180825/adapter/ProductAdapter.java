package com.bwie.tianwang20180825.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.tianwang20180825.R;
import com.bwie.tianwang20180825.bean.CartBean;
import com.bwie.tianwang20180825.jiajian.MyJiaJianView;
import com.bwie.tianwang20180825.view.CartAllCheckboxlistener;
import com.bwie.tianwang20180825.view.CheckListener;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Context mContext;
    List<CartBean.DataBean.ListBean> cartList;
    CheckListener checkListener;
    CartAllCheckboxlistener cartAllCheckboxlistener;

    public void setCartAllCheckboxlistener(CartAllCheckboxlistener cartAllCheckboxlistener) {
        this.cartAllCheckboxlistener = cartAllCheckboxlistener;
    }

    public void setCheckListener(CheckListener checkListener) {
        this.checkListener = checkListener;
    }

    public ProductAdapter(Context context, List<CartBean.DataBean.ListBean> list) {
        this.mContext = context;
        cartList = list;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item2_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.ViewHolder holder, int position) {
        final CartBean.DataBean.ListBean listBean = cartList.get(position);
        holder.priceTv.setText("优惠价:" + listBean.getBargainPrice());
        holder.titleTv.setText(listBean.getTitle());
        String[] split = listBean.getImages().split("\\|");
        if (split != null && split.length > 0) {
            Glide.with(mContext).load(split[0]).into(holder.productIv);
        }
        holder.checkBox.setChecked(listBean.isSelected());
        holder.myJiaJianView.setNumEt(listBean.getTitaLnum());
        holder.myJiaJianView.setJiajianListener(new MyJiaJianView.JiajianListener() {
            @Override
            public void getNum(int num) {
                listBean.setTitaLnum(num);
                if (checkListener != null) {
                    checkListener.notifyParent();
                }
            }
        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    listBean.setSelected(true);
                } else {
                    listBean.setSelected(false);
                }
                if (checkListener != null) {
                    checkListener.notifyParent();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size() == 0 ? 0 : cartList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTv, priceTv;
        ImageView productIv;
        CheckBox checkBox;
        MyJiaJianView myJiaJianView;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.productCheckBox);
            titleTv = itemView.findViewById(R.id.title);
            productIv = itemView.findViewById(R.id.product_icon);
            priceTv = itemView.findViewById(R.id.price);
            myJiaJianView = itemView.findViewById(R.id.jiajianView);

        }
    }
}
