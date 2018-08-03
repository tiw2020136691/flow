package com.bawei.mr.titlebarback;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TitleBar.ClickListener{

    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        titleBar = findViewById(R.id.titleBar);
        titleBar.setClickListener(this);
    }

    @Override
    public void finisActivity() {
        this.finish();
    }
}
