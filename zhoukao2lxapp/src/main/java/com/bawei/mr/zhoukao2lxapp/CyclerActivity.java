package com.bawei.mr.zhoukao2lxapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bawei.mr.zhoukao2lxapp.adapter.ProductAdapter;
import com.bawei.mr.zhoukao2lxapp.api.Api;
import com.bawei.mr.zhoukao2lxapp.bean.ProductBean;
import com.bawei.mr.zhoukao2lxapp.utils.OkHttpUtils;
import com.bawei.mr.zhoukao2lxapp.utils.RequestCallback;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class CyclerActivity extends AppCompatActivity implements ProductAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private ProductBean productBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycler);

        recyclerView = findViewById(R.id.recyclerView);

        HashMap<String, String> params = new HashMap<>();
        params.put("keywords", "手机");
        System.out.println("ssssssssssssssssssssssssssss" + params.toString());
        OkHttpUtils.getInstance().postData(Api.PROUCT_URL, params, new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                if (response.code() == 200) {
                    try {
                        String result = response.body().string();
                        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxx" + result.toString());
                        parseProductBean(result);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void parseProductBean(String result) {
        productBean = new Gson().fromJson(result, ProductBean.class);
        handler.post(new Runnable() {
            @Override
            public void run() {
                fillDatas();
            }
        });
    }

    private void fillDatas() {
        ProductAdapter productAdapter = new ProductAdapter(this, productBean.data);
        productAdapter.setmItemClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productAdapter);

    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        startActivity(new Intent(CyclerActivity.this, QRCodeActivity.class));

    }
}
