package com.bawei.mr.titlebardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.mr.titlebardemo.widget.TitleBar;

public class MainActivity extends AppCompatActivity implements TitleBar.ClickListener {

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
    public void finsihActivity() {
        this.finish();
    }
}
