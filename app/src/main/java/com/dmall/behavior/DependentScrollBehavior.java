package com.dmall.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.View;

/**
 * 1.onStartNestedScroll,我们关心Y轴上的滑动
 * 2.onNestedPreScroll,让关心child滑动起来
 * 3.onNestedFling,当我们松开手指的时候View也可能再滑动一段距离
 * Created by yelong on 16/8/19.
 * mail:354734713@qq.com
 */
public class DependentScrollBehavior extends CoordinatorLayout.Behavior<View> {

    public DependentScrollBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed);
        child.setScrollY(target.getScrollY());
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, View child, View target, float velocityX, float velocityY, boolean consumed) {
        ((NestedScrollView) child).fling((int)velocityY);
        return true;
    }
}
