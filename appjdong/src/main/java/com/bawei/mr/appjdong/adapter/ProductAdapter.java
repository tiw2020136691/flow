package com.bawei.mr.appjdong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.mr.appjdong.R;
import com.bawei.mr.appjdong.bean.ProductBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/8/14.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    Context context;
    List<ProductBean.Product> list;
    OnItemClickListener onItemClickListener;

    public ProductAdapter(Context context, List<ProductBean.Product> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_home, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.MyViewHolder holder, final int position) {
        ProductBean.Product product = list.get(position);
        //with 绑定上下文，load加载网络资源（url），into：把bitmap设置给当前控件
        String[] imageUrls = product.images.split("\\|");

        if (imageUrls != null && imageUrls.length > 0) {

            Glide.with(context).load(imageUrls[0]).into(holder.iv);
        }

        holder.tv.setText(product.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size() == 0 ? 0 : list.size();
    }

    public void loadData(List<ProductBean.Product> data) {

        if (this.list != null) {
            this.list.addAll(data);
            notifyDataSetChanged();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);

            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
