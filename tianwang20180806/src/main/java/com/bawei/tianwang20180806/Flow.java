package com.bawei.tianwang20180806;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by WangZhiQiang on 2018/8/6.
 */
public class Flow extends ViewGroup {

    private int width;
    private int height;

    public Flow(Context context) {
        super(context);
    }

    public Flow(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Flow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeheight = MeasureSpec.getSize(heightMeasureSpec);
        int modeheight = MeasureSpec.getMode(heightMeasureSpec);

        int width = 0;
        int height = 0;

        int linewidth = 0;
        int lineheight = 0;

        int cCount = getChildCount();

        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (linewidth + childWidth > sizeWidth - getPaddingLeft() - getPaddingRight()) {
                width = Math.max(width, linewidth);
                linewidth = childWidth;
                height += lineheight;
                lineheight = childHeight;
            } else {
                linewidth += childWidth;
                height = Math.max(lineheight, childHeight);
            }
            if (i == cCount - 1) {
                width = Math.max(linewidth, width);
                height += lineheight;
            }
        }
        setMeasuredDimension(
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width + getPaddingLeft() + getPaddingRight(),
                modeheight == MeasureSpec.EXACTLY ? sizeheight : height + getPaddingTop() + getPaddingBottom());
    }

    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;

        List<View> linViews = new ArrayList<View>();
        int cCount = getChildCount();
        for (int j = 0; j < cCount; j++) {
            View child = getChildAt(j);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth();
            int childheight = child.getMeasuredHeight();

            if (childWidth + lineWidth + lp.leftMargin + lp.rightMargin > width - getPaddingLeft() - getPaddingRight()) {
                mLineHeight.add(lineHeight);
                mAllViews.add(linViews);

                lineWidth = 0;
                lineHeight = childheight + lp.topMargin + lp.bottomMargin;
                linViews = new ArrayList<View>();
            }
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childheight + lp.topMargin + lp.topMargin);
            linViews.add(child);
        }
        mLineHeight.add(lineHeight);
        mAllViews.add(linViews);

        int top = getPaddingTop();
        int left = getPaddingLeft();

        int lineNum = mAllViews.size();

        for (int j = 0; j < lineNum; j++) {
            linViews = mAllViews.get(j);
            lineHeight = mLineHeight.get(j);
            for (int k = 0; k < linViews.size(); k++) {
                View child = linViews.get(k);

                if (child.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc = lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                child.layout(lc, tc, rc, bc);
                left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            }
            left = getPaddingLeft();
            top += lineHeight;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
