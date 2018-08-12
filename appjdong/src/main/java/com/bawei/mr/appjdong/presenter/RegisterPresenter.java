package com.bawei.mr.appjdong.presenter;

import android.text.TextUtils;

import com.bawei.mr.appjdong.bean.UserBean;
import com.bawei.mr.appjdong.model.RegisterModel;
import com.bawei.mr.appjdong.utils.RegexValidateUtil;
import com.bawei.mr.appjdong.view.IRView;

/**
 * author:Created by WangZhiQiang on 2018/8/9.
 */
public class RegisterPresenter {
    private RegisterModel registerModel;
    private IRView irView;

    public RegisterPresenter( IRView irView) {
        this.registerModel = new RegisterModel();
        this.irView = irView;
    }
    public void register(String mobile,String pwd){
        if (TextUtils.isEmpty(mobile)){
            irView.mobilekong();
            return;
        }
        if (RegexValidateUtil.checkCellphone(mobile)){
            irView.mobilehefa();
            return;
        }
        if (TextUtils.isEmpty(pwd)){
            irView.passhefa();
            return;
        }
        registerModel.register(mobile, pwd, new RegisterModel.RegCallback() {
            @Override
            public void failure(String errorMsg) {
                irView.shibai(errorMsg);
            }

            @Override
            public void success(UserBean userBean) {
                irView.success(userBean);
            }
        });
    }
}
