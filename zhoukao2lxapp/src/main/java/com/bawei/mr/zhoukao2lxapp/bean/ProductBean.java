package com.bawei.mr.zhoukao2lxapp.bean;

import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public class ProductBean {
    public String msg;
    public String code;
    public List<Product> data;

    public class Product{
        public String title;
        public String images;
        public String pid;

    }
}
