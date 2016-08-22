# AndroidBehavior
Android CoordinatorLayout Behavior

# CoordinatorLayout&&Behavior
在使用CoordinatorLayout的时候，一些子View需要在xml中使用下面代码.
其实这个东西是一个类ScrollingViewBehavior，当然我们也可以自定Behavior
`app:layout_behavior="@string/appbar_scrolling_view_behavior"`

# 自定义Behavior
1.情况一，某个View监听另一个View的状态变化，如大小、位置、显示状态等。对于情况一，我们关心的是layoutDependsOn和onDependentViewChanged这两个方法
2.情况二，某个View监听CoordinatorLayout里面的滑动状态。对于情况二，我们关心的是onStartNestedScroll和onNestedPreScroll

>参考资料: </br>http://www.jianshu.com/p/d372d37e8640</br>http://www.jianshu.com/p/5ffb37226e72</br>http://blog.csdn.net/qibin0506/article/details/50377592</br>https://github.com/saulmm/CoordinatorBehaviorExample