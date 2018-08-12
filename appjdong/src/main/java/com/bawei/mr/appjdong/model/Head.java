package com.bawei.mr.appjdong.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.bawei.mr.appjdong.R;

/**
 * author:Created by WangZhiQiang on 2018/8/9.
 */
public class Head extends View {

    private int BitmapWidth = 0;
    private int BitmapHeight = 0;
    private BitmapShader bitmapShader;
    private ShapeDrawable shapeDrawable;

    public Head(Context context) {
        super(context);
       Bitmap bitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.ic_launcher_background)).getBitmap();
        BitmapWidth = bitmap.getWidth();
        BitmapHeight = bitmap.getHeight();

        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.MIRROR,Shader.TileMode.REPEAT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        shapeDrawable = new ShapeDrawable(new OvalShape());
        //得到画笔并设置渲染器
        shapeDrawable.getPaint().setShader(bitmapShader);
        //设置显示区域
        shapeDrawable.setBounds(20, 20,700,BitmapHeight);//可见区域
        //绘制shapeDrawable
        shapeDrawable.draw(canvas);
    }

    public Head(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Head(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
