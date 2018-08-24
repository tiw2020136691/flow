package com.example.myflow.adapter;

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
import com.example.myflow.R;
import com.example.myflow.bean.CartBean;
import com.example.myflow.jiajianVie.MyJiaJianView;
import com.example.myflow.view.CartAllCheckboxListener;
import com.example.myflow.view.CartCheckListener;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CartViewHolder> {
    Context mContext;
    List<CartBean.DataBean.ListBean> listBeanList;
    CartAllCheckboxListener cartAllCheckboxListener;
    CartCheckListener cartCheckListener;

    public ProductAdapter(Context context, List<CartBean.DataBean.ListBean> list) {
        mContext = context;
        listBeanList = list;
    }

    public void setCartAllCheckboxListener(CartAllCheckboxListener cartAllCheckboxListener) {
        this.cartAllCheckboxListener = cartAllCheckboxListener;
    }

    public void setCartCheckListener(CartCheckListener cartCheckListener) {
        this.cartCheckListener = cartCheckListener;
    }

    @NonNull
    @Override
    public ProductAdapter.CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.product_item_layou, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(itemView);
        return cartViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProductAdapter.CartViewHolder holder, int position) {
        final CartBean.DataBean.ListBean bean = listBeanList.get(position);
        holder.priceTv.setText("优惠价：￥" + bean.getBargainPrice());
        holder.titleTv.setText(bean.getTitle());
        String[] imgs = bean.getImages().split("\\|");
        if (imgs != null && imgs.length > 0) {
            Glide.with(mContext).load(imgs[0]).into(holder.productIv);
        } else {
            holder.productIv.setImageResource(R.mipmap.ic_launcher);
        }
        holder.checkBox.setChecked(bean.isSelected());

//        holder.myJiaJianView.setNumEt(bean.getTotalNum());

//        holder.myJiaJianView.setJianJianListener(new MyJiaJianView.JianJianListener() {
//            @Override
//            public void getNum(int num) {
//                bean.setTotalNum(num);
//                if (cartCheckListener != null) {
//                    cartCheckListener.notifyParent();
//                }
//            }
//        });
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkBox.isChecked()) {
                    bean.setSelected(true);
                } else {
                    bean.setSelected(false);
                }
                if (cartCheckListener != null) {
                    cartCheckListener.notifyParent();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return listBeanList.size() == 0 ? 0 : listBeanList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView titleTv, priceTv;
        ImageView productIv;
        private MyJiaJianView myJiaJianView;

        public CartViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.productCheckBox);
            titleTv = itemView.findViewById(R.id.title);
            priceTv = itemView.findViewById(R.id.price);
            productIv = itemView.findViewById(R.id.product_icon);
            myJiaJianView = itemView.findViewById(R.id.jiajianView);

        }
    }
}
