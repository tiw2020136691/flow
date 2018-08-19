package com.bawei.mr.appjdong.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bawei.mr.appjdong.R;
import com.bawei.mr.appjdong.bean.HomeEntity;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/8/16.
 */
public class XRcyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int BANNER_VIEW_TYPE = 0;//轮播图
    private final int CLASSES_VIEW_TYPE = 1;//频道
    private Context context;
    private HomeEntity homeEntity;
    private List<String> imageList;
    private View view;


    public XRcyclerViewAdapter(Context context, HomeEntity homeEntity) {
        this.context = context;
        this.homeEntity = homeEntity;
        imageList = new ArrayList<>();
        for (HomeEntity.Home.Banner banner : homeEntity.data.banner) {
            imageList.add(banner.icon);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == BANNER_VIEW_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.home_banner,parent,false);

            BannerHolder bannerHolder = new BannerHolder(view);
            return bannerHolder;
        } else if (viewType == CLASSES_VIEW_TYPE) {
            view = LayoutInflater.from(context).inflate(R.layout.classes_layout,parent,false);
            ClassesHolder classesHolder = new ClassesHolder(view);
            return classesHolder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BannerHolder){
//            ((BannerHolder) holder).banner.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
            //设置图片加载器
            ((BannerHolder) holder).banner.setImageLoader(new GlideImageLoader());
            //设置图片集合
            ((BannerHolder) holder).banner.setImages(imageList);
            //设置动画效果
            ((BannerHolder) holder).banner.setBannerAnimation(com.youth.banner.Transformer.DepthPage);
            //设置轮播时间
            ((BannerHolder) holder).banner.setDelayTime(3000);
            //设置自动轮播，默认为true
            ((BannerHolder) holder).banner.isAutoPlay(true);
            //设置指示器位置（当banner模式中有指示器时）
            ((BannerHolder) holder).banner.setIndicatorGravity(BannerConfig.CENTER);
//                //设置标题集合（当banner样式有显示title时）
//            ((BannerHolder) holder).banner.setBannerTitles(imageList);

            //banner设置方法全部调用完毕时最后调用
            ((BannerHolder) holder).banner.start();

//            ((BannerHolder) holder).banner.setImages(imageList);

        }else if (holder instanceof ClassesHolder){
//((ListHolder) holder).xRecy.setAdapter();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER_VIEW_TYPE;
        } else if (position == 1) {
            return CLASSES_VIEW_TYPE;
        } else {
            return super.getItemViewType(position);
        }
    }


    /*****************************************下面是为不同的布局创建不同的ViewHolder*******************************************************/
    /**
     * 轮播图的ViewHolder
     */
    public static class BannerHolder extends RecyclerView.ViewHolder {

        Banner banner;

        public BannerHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);

        }
    }
    /*****************************************下面是为不同的布局创建不同的ViewHolder*******************************************************/
    /**
     * 分类的ViewHolder
     */
    public static class ClassesHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ClassesHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.calsses_xv);

        }
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
