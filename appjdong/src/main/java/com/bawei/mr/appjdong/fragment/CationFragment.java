package com.bawei.mr.appjdong.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.bawei.mr.appjdong.R;


/**
 * author:Created by WangZhiQiang on 2018/8/9.
 */
public class CationFragment extends Fragment {

    private FrameLayout fragmeLeft;
    private FrameLayout fragmeRight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_cation, container, false);
        fragmeLeft = view.findViewById(R.id.fragmentLeft);
        fragmeRight = view.findViewById(R.id.fragmentRight);
        return view;
    }
}
