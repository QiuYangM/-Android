package android.wan.com.wanandroid.base

import android.app.Application
import org.litepal.LitePal

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/5/31$ 23:25$.
 */
class BaseApp :Application() {

    override fun onCreate() {
        super.onCreate()

        //初始LitePal数据库
        LitePal.initialize(this)
    }

}