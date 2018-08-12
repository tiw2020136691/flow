package com.bawei.mr.zhoukao2lxapp.view;

import com.bawei.mr.zhoukao2lxapp.bean.UserBean;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public interface IRView {

    void mobileVerify();//校验手机号合法
    void mobileEmpty();//非空
    void pwdVerify();//校验密码
    void success(UserBean userBean);//请求成功
    void failure(String msg);//请求失败
}
