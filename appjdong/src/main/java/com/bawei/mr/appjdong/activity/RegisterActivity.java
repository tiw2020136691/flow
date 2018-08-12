package com.bawei.mr.appjdong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.bawei.mr.appjdong.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerEt = findViewById(R.id.registerEt);
    }

    public void register(View view) {
        String s = registerEt.getText().toString();
        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        finish();
    }
}
