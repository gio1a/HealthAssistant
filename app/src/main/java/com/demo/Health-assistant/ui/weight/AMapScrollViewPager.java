package com.demo.motion.ui.weight;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;


public class AMapScrollViewPager extends ViewPager {

    public AMapScrollViewPager(Context context) {
        super(context);
    }

    public AMapScrollViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean canScroll(View v, boolean checkV, int dx, int x, int y) {
        if (Math.abs(dx) > 50) {
            return super.canScroll(v, checkV, dx, x, y);
        } else {
            return true;
        }
    }
}
