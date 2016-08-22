package com.dmall.behavior;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * 在构造方法里面可以获取一些东西,譬如获取依赖控件的Id
 * Created by yelong on 16/8/19.
 * mail:354734713@qq.com
 */
public class FooterBehavior extends CoordinatorLayout.Behavior<View> {

    private int anchorId;

    public FooterBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (null != attrs) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FooterBehavior);
            anchorId = array.getResourceId(R.styleable.FooterBehavior_fbAnchorId, -1);
            array.recycle();
        }
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //在外部传入依赖id具有更高的灵活性
        if (anchorId > 0) return dependency.getId() == anchorId;
        return dependency instanceof AppBarLayout;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //float translationY = Math.abs(dependency.getTranslationY());
        //child.setTranslationY(translationY);
        //System.out.println("translationY==" + translationY);
        child.setTranslationY(-dependency.getTop());
        return true;
    }
}
