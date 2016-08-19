package com.dmall.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 1.有参构造函数必须重载,因为CoordinatorLayout里利用反射去获取这个Behavior的时候就是拿的这个构造
 * 2.parent参数不用说就是CoordinatorLayout
 * 3.child参数,就是我们设置behavior的View
 * 4.dependency,就是我们关心的View.layoutDependsOn的返回值决定
 * Created by yelong on 16/8/19.
 * mail:354734713@qq.com
 */
public class DependentBehavior extends CoordinatorLayout.Behavior<View> {

    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child, offset);
        return true;
    }
}
