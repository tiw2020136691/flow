package com.bawei.mr.appjdong.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.mr.appjdong.R;
import com.bawei.mr.appjdong.adapter.XRcyclerViewAdapter;
import com.bawei.mr.appjdong.bean.HomeEntity;
import com.bawei.mr.appjdong.common.Api;
import com.bawei.mr.appjdong.utils.OkHttpUtils;
import com.bawei.mr.appjdong.utils.RequestCallback;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

/**
 * author:Created by WangZhiQiang on 2018/8/9.
 */
public class HomeFragment extends Fragment {


    private XRecyclerView.LayoutManager mLayoutManager;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private XRcyclerViewAdapter mAdapter;
    private XRecyclerView xRecyclerView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        xRecyclerView = view.findViewById(R.id.xRecyclerView);

        mLayoutManager = new LinearLayoutManager(getActivity());
        xRecyclerView.setLayoutManager(mLayoutManager);

        initData();

        return view;
    }

    private void initData() {
        OkHttpUtils.getInstance().postData(Api.IMAGE_URL, new HashMap<String, String>(), new RequestCallback() {
            @Override
            public void failure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) {
                if (200==response.code()){
                    String result = null;
                    try {
                        result = response.body().string();
                        if (!TextUtils.isEmpty(result)){
                            parseJsonResult(result);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    //解析json串
    private void parseJsonResult(String result) {
        final HomeEntity homeBean = new Gson().fromJson(result, HomeEntity.class);
        if(homeBean!=null){

            handler.post(new Runnable() {
                @Override
                public void run() {
                    mAdapter = new XRcyclerViewAdapter(getActivity(),homeBean);
                    xRecyclerView.setAdapter(mAdapter);
                }
            });
        }
    }

}
