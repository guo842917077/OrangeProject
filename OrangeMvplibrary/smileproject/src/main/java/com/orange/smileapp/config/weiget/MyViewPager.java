package com.orange.smileapp.config.weiget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2016/6/1.
 */
public class MyViewPager extends ViewPager {
    float minDistance;//最小的滑动距离
    private float down_positon;//按下的距离

    public MyViewPager(Context context) {
        this(context,null);
    }
    public MyViewPager (Context context, AttributeSet attrs)
    {
        super (context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            down_positon = ev.getX();
        }
        /**
         * 如果滑动的距离（从按下位置到目前位置）超过最小的滑动距离，认为是viewpager滑动
         * 拦截事件，否则认为控件滑动，不拦截该事件
         */
        if (ev.getAction() == MotionEvent.ACTION_MOVE) {
            if (Math.abs(ev.getX() - down_positon) > minDistance)
                return false;
            else
                return false;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
