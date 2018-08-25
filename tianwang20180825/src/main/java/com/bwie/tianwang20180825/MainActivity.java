package com.bwie.tianwang20180825;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fynn.fluidlayout.FluidLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String News[] = {"手机", "笔记本", "小吃", "夜宵", "餐馆", "圣地"};
    private FluidLayout liu;
    private FluidLayout fluidLayout;
    private EditText editText;
    private List<String> list;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 找到控件
     */
    private void initView() {
        button = findViewById(R.id.sousuo);
        liu = findViewById(R.id.liu);
        fluidLayout = findViewById(R.id.liushi);
        editText = findViewById(R.id.souzhuo);
        list = new ArrayList<>();
    }

    private void initData() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String liua[] = {s};
                for (int i = 0; i < liua.length; i++) {
                    FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(12, 12, 12, 12);
                    TextView tv = new TextView(MainActivity.this);
                    tv.setText(liua[i]);
                    liu.addView(tv, params);
                }
            }
        });

        for (int i = 0; i < News.length; i++) {
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 12, 12, 12);
            TextView textView = new TextView(MainActivity.this);
            textView.setText(News[i]);
            fluidLayout.addView(textView, params);
        }
    }

    /**
     * 跳转到购物车
     *
     * @param view
     */
    public void go(View view) {
        startActivity(new Intent(MainActivity.this, CartActivity.class));
    }

    /**
     * 删除搜索的内容
     *
     * @param view
     */
    public void delete(View view) {

    }
}
