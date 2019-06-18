package android.wan.com.wanandroid.utils.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/10$ 0:19$.
 */
class LoginPersistence(context: Context) {
    private val evidence = "shard_login"

    private val loginData = evidence + "_login"

    private var login: SharedPreferences? = null

    init {
        login = context.getSharedPreferences(evidence, Context.MODE_PRIVATE)
    }

    /**
     * 保存登录的用户信息
     */
    @SuppressLint("CommitPrefEdits")
    fun save(json: String) {
        login?.edit()?.putString(loginData, json).apply { }
    }

    /**
     * 清除登录数据
     */
    fun delete() {
        login.let {
            it?.edit()?.putString(loginData, "")?.apply()
        }
    }

    /**
     * 获取用户数据
     */
    fun getData(): String {
        var data = ""
        when (login?.getString(loginData, "")) {
            "" -> data = "0"
            else -> {
                return data
            }
        }
        return data
    }
}