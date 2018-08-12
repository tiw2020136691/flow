package com.bawei.mr.appjdong.view;

import com.bawei.mr.appjdong.bean.UserBean;

/**
 * author:Created by WangZhiQiang on 2018/8/9.
 */
public interface IRView {
    void mobilehefa();
    void mobilekong();
    void passhefa();
    void success(UserBean userBean);
    void shibai(String msg);
}
