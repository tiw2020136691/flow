package com.bawei.mr.zhoukao2lxapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.mr.zhoukao2lxapp.R;
import com.bawei.mr.zhoukao2lxapp.bean.ProductBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> implements View.OnClickListener {

    Context context;
    List<ProductBean.Product> list;
    private OnItemClickListener mItemClickListener;

    public ProductAdapter(Context context, List<ProductBean.Product> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(this);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ProductBean.Product product = list.get(position);
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaa"+product);
        String[] imageUrls = product.images.split("\\|");
        if (imageUrls != null && imageUrls.length>0){
            Glide.with(context).load(imageUrls[0]).into(holder.iv);
        }
        holder.tv.setText(product.title);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return list.size() == 0?0 : list.size();
    }
    public void loadData(List<ProductBean.Product> data){
        if (this.list != null){
            this.list.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View view) {
        if (mItemClickListener!=null){
            mItemClickListener.onItemClick((Integer)
                    view.getTag());
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;
        private TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public void setmItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
