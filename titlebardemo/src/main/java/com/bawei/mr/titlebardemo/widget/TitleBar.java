package com.bawei.mr.titlebardemo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.mr.titlebardemo.R;

/**
 * author:Created by WangZhiQiang on 2018/8/3.
 */
public class TitleBar extends FrameLayout {

    private ImageView backIv;
    private TextView titleTv;
    private TextView addTv;
    ClickListener clickListener;
    private int dimensionPixelSize;
    private int color;

    public TitleBar(@NonNull Context context) {
        this(context, null);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    /**
     * 初始化数据
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        initAttes(context, attrs);
        initView();
    }

    /**
     * 初始化组合空间的view
     */
    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.title_bar, this, true);
        backIv = view.findViewById(R.id.back_iv);
        titleTv = view.findViewById(R.id.title_tv);
        addTv = view.findViewById(R.id.add_tv);

        backIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener != null) {
                    clickListener.finsihActivity();
                }
            }
        });
    }

    public interface ClickListener {
        void finsihActivity();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 初始化控件属性
     *
     * @param context
     * @param attrs
     */
    private void initAttes(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TitleBar);
        color = typedArray.getColor(R.styleable.TitleBar_titleColor, getResources().getColor(R.color.colorAccent));
        dimensionPixelSize = typedArray.getDimensionPixelSize(R.styleable.TitleBar_titleSize, 16);
        typedArray.recycle();
    }
}
