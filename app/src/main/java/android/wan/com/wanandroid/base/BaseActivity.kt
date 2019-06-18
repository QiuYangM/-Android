package android.wan.com.wanandroid.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.WindowManager
import android.wan.com.wanandroid.R
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/5/31$ 23:26$.
 */
abstract class BaseActivity : AppCompatActivity() {

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //TODO 5.0以上 有效
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        //设置状态栏颜色
        window.statusBarColor = resources.getColor(R.color.colorPrimary)

        setContentView(setLayoutId())

        getIntentData()

        initView()
    }

    abstract fun initView()

    protected fun getIntentData() {

    }

    fun shortToast(msg: String) {
        runOnUiThread {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        }
    }

    abstract fun setLayoutId(): Int
}