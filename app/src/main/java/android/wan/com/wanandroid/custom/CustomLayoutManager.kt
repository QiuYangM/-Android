package android.wan.com.wanandroid.custom

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/3$ 16:13$.
 */
class CustomLayoutManager : LinearLayoutManager {

    private var flag: Boolean = true

    constructor(context: Context?) : super(context)
    constructor(context: Context?, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun setScroolEnable(falg: Boolean) {
        this.flag = falg
    }

    override fun canScrollVertically(): Boolean {
        return this.flag && super.canScrollVertically()
    }
}