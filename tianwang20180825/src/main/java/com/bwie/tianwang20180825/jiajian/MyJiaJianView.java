package com.bwie.tianwang20180825.jiajian;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.tianwang20180825.R;

public class MyJiaJianView extends LinearLayout {

    private TextView jiaTv;
    private TextView jianTv;
    private EditText numEt;
    private int num = 1;

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
        View inflate = LayoutInflater.from(context).inflate(R.layout.jia_jian_layout, this, false);
        addView(inflate);
        jianTv = inflate.findViewById(R.id.jianTv);
        jiaTv = inflate.findViewById(R.id.jiaTv);
        numEt = inflate.findViewById(R.id.num);

        numEt.setText(num + "");
        jiaTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                numEt.setText(num + "");
                if (jiajianListener != null) {
                    jiajianListener.getNum(num);
                }
            }
        });
        jianTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num <= 0) {
                    Toast.makeText(context, "商品数量不能小于1", Toast.LENGTH_SHORT).show();
                    num = 1;
                }
                numEt.setText(num + "");
                if (jiajianListener != null) {
                    jiajianListener.getNum(num);
                }
            }
        });
    }

    public void setNumEt(int n) {
        numEt.setText(n + "");
        num = Integer.parseInt(numEt.getText().toString());
    }

    private JiajianListener jiajianListener;

    public void setJiajianListener(JiajianListener jiajianListener) {
        this.jiajianListener = jiajianListener;
    }

    public interface JiajianListener {
        void getNum(int num);
    }
}
