package com.example.myflow;

import android.content.Intent;
import android.graphics.Color;
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
    private String mVals[] = {"苹果手机", "笔记本电脑", "电饭煲 ", "腊肉",
            "特产", "剃须刀", "宝宝", "康佳", "特产", "剃须刀", "宝宝", "康佳"};
    private FluidLayout liu;
    private FluidLayout fluidLayout;
    private Button button;
    private EditText editText;
    private List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        editText = findViewById(R.id.souzhou);
        button = findViewById(R.id.sousuo);
        fluidLayout = findViewById(R.id.liushi);
        liu = findViewById(R.id.liu);

        list = new ArrayList<>();

    }
    /**
     * 搜索
     */
    private void initData() {

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                String lius[] = {s};
                for (int i = 0; i < lius.length; i++) {
                    FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.setMargins(12, 12, 12, 12);
                    TextView textView = new TextView(MainActivity.this);
                    textView.setText(lius[i]);
//                    textView.setBackgroundResource(BLUE);
                    liu.addView(textView, params);
                }
            }
        });
        /**
         * 流式布局第三方
         */
        for (int i = 0; i < mVals.length; i++) {
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(12, 12, 12, 12);
            TextView textView = new TextView(MainActivity.this);
            textView.setText(mVals[i]);
            textView.setTextColor(Color.BLACK);
            fluidLayout.addView(textView, params);
        }
    }

    /**
     * 跳转到购物车页面
     *
     * @param view
     */
    public void tiao(View view) {
        startActivity(new Intent(MainActivity.this, CartActivity.class));
    }
}
