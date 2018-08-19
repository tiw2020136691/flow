package com.bawei.mr.appjdong;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.bawei.mr.appjdong.fragment.CartFragment;
import com.bawei.mr.appjdong.fragment.FindFragment;
import com.bawei.mr.appjdong.fragment.HomeFragment;
import com.bawei.mr.appjdong.fragment.MeFragment;

public class MainActivity extends AppCompatActivity {


    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        radioGroup = findViewById(R.id.rg);
        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new HomeFragment()).commit();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb01:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new HomeFragment()).commit();
                        break;
                    case R.id.rb02:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new CartFragment()).commit();
                        break;
                    case R.id.rb03:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new FindFragment()).commit();
                        break;
                    case R.id.rb04:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new CartFragment()).commit();
                        break;
                    case R.id.rb05:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frag, new MeFragment()).commit();
                        break;
                }
            }
        });
    }
}
