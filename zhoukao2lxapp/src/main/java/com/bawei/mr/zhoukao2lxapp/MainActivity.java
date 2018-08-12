package com.bawei.mr.zhoukao2lxapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.mr.zhoukao2lxapp.bean.UserBean;
import com.bawei.mr.zhoukao2lxapp.presenter.RegisterPresenter;
import com.bawei.mr.zhoukao2lxapp.view.IRView;

public class MainActivity extends AppCompatActivity implements IRView{

    private EditText mobileEt;
    private EditText pwdEt;
    RegisterPresenter registerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        mobileEt = findViewById(R.id.mobileEt);
        pwdEt = findViewById(R.id.pwdEt);
    }

    private void initData() {
        registerPresenter = new RegisterPresenter(this);
    }


    public void register(View view) {
        registerPresenter.register(mobileEt.getText().toString(),pwdEt.getText().toString());
    }

    @Override
    public void mobileVerify() {
        Toast.makeText(this, "手机号必须11位", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobileEmpty() {
        Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdVerify() {
        Toast.makeText(this, "密码不合法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserBean userBean) {
        Toast.makeText(this, userBean.msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
