package com.bawei.mr.zhoukao2lxapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.bawei.mr.zhoukao2lxapp.bean.LoginBean;
import com.bawei.mr.zhoukao2lxapp.presenter.LoginPresenter;
import com.bawei.mr.zhoukao2lxapp.view.LoginView;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements LoginView{

    private EditText mobileEt;
    private EditText pwdEt;
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initView() {
        mobileEt = findViewById(R.id.mobileEt);
        pwdEt = findViewById(R.id.pwdEt);
    }

    private void initData() {
        loginPresenter = new LoginPresenter(this);
    }

    public void login(View view) {
        String mobile = mobileEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        HashMap<String,String> params = new HashMap<>();
        params.put("mobile",mobile);
        params.put("pwd",pwd);
        loginPresenter.login(params);
    }

    @Override
    public void mobileVerify() {

    }

    @Override
    public void mobileEmpty() {

    }

    @Override
    public void pwdVerify() {

    }

    @Override
    public void success(LoginBean userBean) {
        startActivity(new Intent(LoginActivity.this,CyclerActivity.class));
    }

    @Override
    public void failure(String msg) {

    }
}
