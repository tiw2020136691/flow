package com.bawei.mr.appjdong.bean;

import java.util.List;


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
