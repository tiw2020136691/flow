package com.bawei.mr.zhoukao2lxapp.bean;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public class LoginBean {
    public String msg;
    public String code;
    public User data;

    public class User{
        public String nickname;
        public String icon;
        public String mobile;
    }
}
