package com.bawei.mr.appjdong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.bawei.mr.appjdong.MainActivity;
import com.bawei.mr.appjdong.R;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity {

    private ViewPager viewPager;
    List<View> iViews = new ArrayList<>();
    private PagerAdapter mAdapter;
    private Button btnInto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.viewPager);
        LayoutInflater inflater = LayoutInflater.from(this);
        View guideOne = inflater.inflate(R.layout.guidance01, null);
        View guideTwo = inflater.inflate(R.layout.guidance02, null);
        View guideThree = inflater.inflate(R.layout.guidance03, null);

        iViews.add(guideOne);
        iViews.add(guideTwo);
        iViews.add(guideThree);

        mAdapter = new PagerAdapter() {
            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = iViews.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                View view = iViews.get(position);
                container.removeView(view);
            }

            @Override
            public int getCount() {
                return iViews.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }
        };
        viewPager.setAdapter(mAdapter);
        btnInto = guideThree.findViewById(R.id.to_Main);
        btnInto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(GuideActivity.this, "toMain", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
