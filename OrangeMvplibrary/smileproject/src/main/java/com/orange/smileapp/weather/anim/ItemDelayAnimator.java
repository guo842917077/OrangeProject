package com.orange.smileapp.weather.anim;

import android.support.v7.widget.RecyclerView;
import android.view.animation.DecelerateInterpolator;

/**
 * 延时进入效果
 */
public class ItemDelayAnimator {
    static public RecyclerView.ItemAnimator slidein() {
        SlideInUpDelayedAnimator animator = new SlideInUpDelayedAnimator(new DecelerateInterpolator(1.2f));
        animator.setAddDuration(600);
        return animator;
    }
}
