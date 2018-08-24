package com.example.myflow.jiajianVie;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myflow.R;

public class MyJiaJianView extends LinearLayout {
    TextView jiaTv, jianTv;
    EditText numEt;
    int num = 1;

    public MyJiaJianView(Context context) {
        this(context, null);
    }

    public MyJiaJianView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyJiaJianView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.jia_jian_layout, this, false);
        addView(view);
        jiaTv = view.findViewById(R.id.jia);
        jianTv = view.findViewById(R.id.jian);
        numEt = view.findViewById(R.id.num);

        numEt.setText(num + "");

        jiaTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                numEt.setText(num + "");
//                if (jianJianListener != null) {
//                    jianJianListener.getNum(num);
//                }
            }
        });
        jianTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num <= 0) {
                    Toast.makeText(context, "数量不能小于1", Toast.LENGTH_SHORT).show();
                    num = 1;
                }
                numEt.setText(num + "");
//                if (jianJianListener != null) {
//                    jianJianListener.getNum(num);
//                }
            }
        });
    }

//    public void setNumEt(int n) {
//        numEt.setText(n + "");
//        num = Integer.parseInt(numEt.getText().toString());
//    }
//
//    private JianJianListener jianJianListener;
//
//    public void setJianJianListener(JianJianListener jianJianListener) {
//        this.jianJianListener = jianJianListener;
//    }
//
//    public interface JianJianListener {
//        void getNum(int num);
//    }num
}
