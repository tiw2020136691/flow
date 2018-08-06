package com.bawei.tianwang20180806;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * author:Created by WangZhiQiang on 2018/8/6.
 */
public class Title extends LinearLayout {

    private EditText titleEt;
    private TextView titleTv;
    private int dimensionPixelSize;
    ClickListenter clickListenter;

    public Title(Context context) {
        this(context,null);
    }

    public Title(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public Title(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttrs(context,attrs);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_item,this,true);
        titleEt = view.findViewById(R.id.title_Et);
        titleTv = view.findViewById(R.id.title_tv);

        titleTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListenter != null){
                    clickListenter.finisActivity();
                }
            }
        });
    }
    public interface ClickListenter{
        void finisActivity();
    }

    public void setClickListenter(ClickListenter clickListenter) {
        this.clickListenter = clickListenter;
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Title);
        int color = typedArray.getColor(R.styleable.Title_titleColor, getResources().getColor(R.color.colorAccent));
        dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.Title_titleSize, 16);

        typedArray.recycle();
    }


}
