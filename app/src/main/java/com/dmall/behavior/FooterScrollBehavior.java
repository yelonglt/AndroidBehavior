package com.dmall.behavior;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;

/**
 * 注意点:
 * 1.滚动控件必须实现NestedScrollingChild接口
 * 2.没有实现该接口且不调用dispatchNestedScroll相关接口的滚动控件如ScrollView、WebView、ListView是没有作用的
 * Created by yelong on 16/8/19.
 * mail:354734713@qq.com
 */
public class FooterScrollBehavior extends CoordinatorLayout.Behavior<View> {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean isAnimated;

    public FooterScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //1.判断滑动的方向 我们需要垂直滑动
    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    //2.根据滑动的距离显示和隐藏子视图
    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        System.out.println("child.getTranslationY==" + child.getTranslationY());
        if (dy > 0 && !isAnimated && child.getTranslationY() < child.getHeight()) {
            child.setTranslationY(child.getTranslationY() + dy);
        } else if (dy < 0 && !isAnimated && child.getTranslationY() > 0) {//下滑
            child.setVisibility(View.VISIBLE);
            if (child.getTranslationY() + dy < 0) {
                child.setTranslationY(0);
            } else {
                child.setTranslationY(child.getTranslationY() + dy);
            }
        }
    }

    @Override
    public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target) {
        if (child.getTranslationY() < child.getHeight() / 2) {
            changeState(child, 0);
        } else {
            changeState(child, child.getHeight());
        }
    }

    /**
     * 改变子视图的状态
     *
     * @param child   使用FooterScrollBehavior的视图
     * @param scrollY 垂直方向的偏移量
     */
    private void changeState(final View child, final int scrollY) {
        ViewPropertyAnimator animator = child.animate()
                .translationX(scrollY).setInterpolator(INTERPOLATOR)
                .setDuration(200 * scrollY / child.getHeight());
        animator.setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                isAnimated = true;
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (child.getTranslationY() == child.getHeight()) {
                    child.setVisibility(View.GONE);
                }
                isAnimated = false;
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                child.setTranslationY(scrollY);
            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animator.start();
    }

}
