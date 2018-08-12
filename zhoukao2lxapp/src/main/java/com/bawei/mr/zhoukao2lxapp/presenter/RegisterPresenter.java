package com.bawei.mr.zhoukao2lxapp.presenter;

import android.text.TextUtils;

import com.bawei.mr.zhoukao2lxapp.bean.UserBean;
import com.bawei.mr.zhoukao2lxapp.model.RegisterModel;
import com.bawei.mr.zhoukao2lxapp.utils.RegexValidateUtil;
import com.bawei.mr.zhoukao2lxapp.view.IRView;

/**
 * author:Created by WangZhiQiang on 2018/8/11.
 */
public class RegisterPresenter {

    private RegisterModel registerModel;
    private IRView irView;

    public RegisterPresenter(IRView irView) {
        this.registerModel = new RegisterModel();
        this.irView = irView;
    }

    public void register(String mobile, String pwd) {
        if (TextUtils.isEmpty(mobile)) {
            irView.mobileEmpty();
            return;
        }
        if (!RegexValidateUtil.checkCellphone(mobile)) {
            irView.mobileVerify();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            irView.pwdVerify();
            return;
        }

//        HashMap<String, String> params = new HashMap<>();
//        params.put("mobile", mobile);
//        params.put("password", pwd);

        registerModel.register(mobile,pwd, new RegisterModel.RegCallback() {
            @Override
            public void failure(String ErrorMsg) {
                irView.failure(ErrorMsg);
            }

            @Override
            public void success(UserBean userBean) {
                irView.success(userBean);
            }
        });
    }
}
