package com.dmall.behavior;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yelong on 16/8/19.
 * mail:354734713@qq.com
 */
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //float translationY = Math.abs(dependency.getTranslationY());
        //child.setTranslationY(translationY);
        float translationY = Math.abs(dependency.getBottom());
        child.setTranslationY(360 - translationY);
        System.out.println("translationY==" + translationY);
        return true;
    }
}
