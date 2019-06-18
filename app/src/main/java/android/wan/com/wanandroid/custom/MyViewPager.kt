package android.wan.com.wanandroid.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager


/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/1$ 22:47$.
 */
class MyViewPager : ViewPager {

    private var isScoll = false

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)


    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        return this.isScoll && super.onTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return this.isScoll && super.onInterceptTouchEvent(ev)
    }

    /**
     * 设置是否滑动
     * flag
     */
    public fun setScoll(flag: Boolean) {
        this.isScoll = flag
    }

}