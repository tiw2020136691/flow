package com.bawei.mr.appjdong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.mr.appjdong.MainActivity;
import com.bawei.mr.appjdong.R;
import com.bawei.mr.appjdong.bean.UserBean;
import com.bawei.mr.appjdong.presenter.RegisterPresenter;
import com.bawei.mr.appjdong.view.IRView;

public class LoginActivity extends AppCompatActivity implements IRView{

    private EditText loginEt;
    private EditText loginPass;
    private RegisterPresenter registerPresenter;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initData();
        initView();
    }

    private void initView() {
        loginEt = findViewById(R.id.loginEt);
        loginPass = findViewById(R.id.loginPass);
        tvRegister = findViewById(R.id.tvRegister);

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });
    }

    private void initData() {
        registerPresenter = new RegisterPresenter(this);
    }

    public void login(View view) {
        registerPresenter.register(loginEt.getText().toString(),loginPass.getText().toString());


    }

    @Override
    public void mobilehefa() {
        Toast.makeText(this, "手机号必须是11位数", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void mobilekong() {
        Toast.makeText(this, "手机号不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passhefa() {
        Toast.makeText(this, "密码不合法", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserBean userBean) {
        Toast.makeText(this, userBean.msg, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void shibai(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
