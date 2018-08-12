package com.bawei.mr.zhoukao2lxapp.view;

import com.bawei.mr.zhoukao2lxapp.bean.LoginBean;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public interface LoginView {

    void mobileVerify();//校验手机号合法

    void mobileEmpty();

    void pwdVerify();//校验密码

    void success(LoginBean userBean);//请求成功

    void failure(String msg);//请求失败
}
