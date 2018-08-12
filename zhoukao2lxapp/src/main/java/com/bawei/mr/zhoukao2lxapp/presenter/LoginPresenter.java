package com.bawei.mr.zhoukao2lxapp.presenter;

import com.bawei.mr.zhoukao2lxapp.bean.LoginBean;
import com.bawei.mr.zhoukao2lxapp.model.LoginModel;
import com.bawei.mr.zhoukao2lxapp.view.LoginView;

import java.util.HashMap;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public class LoginPresenter {
    LoginModel loginModel;
    LoginView loginView;

    public LoginPresenter( LoginView loginView) {
        this.loginModel = new LoginModel();
        this.loginView = loginView;
    }

    public void login(HashMap<String,String> params){
        loginModel.login(params, new LoginModel.LoginCallback() {
            @Override
            public void failure(String errorMsg) {
                loginView.failure(errorMsg);
            }

            @Override
            public void LoginSuccess(LoginBean loginBean) {
                loginView.success(loginBean);
            }
        });
    }
}
