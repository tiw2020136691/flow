package com.bawei.tianwang20180806;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

public class MainActivity extends AppCompatActivity implements Title.ClickListenter {

    private Title title;
    private String[] mVals = new String[]{"苹果，电脑，游戏，水果"};
    private LayoutInflater mInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInflater = LayoutInflater.from(this);

        initView();
    }

    private void initView() {
        title = findViewById(R.id.title);
        for (int i = 0; i < mVals.length; i++) {
        }

    }

    @Override
    public void finisActivity() {
        this.finish();
    }
}
