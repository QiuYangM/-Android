package android.wan.com.wanandroid.ui.activity

import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.base.BaseActivity
import android.wan.com.wanandroid.bean.LoginBean
import android.wan.com.wanandroid.net.API
import android.wan.com.wanandroid.net.HttpHelper
import android.wan.com.wanandroid.utils.utils.LoginPersistence
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_login.*

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/9$ 23:58$.
 */
class LoginActivity : BaseActivity() {
    override fun initView() {
        to_login.setOnClickListener {
            toLogin(get_account.text.toString(), get_password.text.toString())
        }
    }

    /**
     * 请求登录
     */
    private fun toLogin(userName: String?, passWord: String?) {
        val map = HashMap<String, String>()
        userName?.let { map.put("username", it) }
        passWord?.let { map.put("password", it) }
        HttpHelper().result(object : HttpHelper.HttpHelperCallBack {
            override fun success(string: String) {
                val bean = Gson().fromJson(string, LoginBean::class.java)
                when (bean.errorCode) {
                    0 -> {
                        LoginPersistence(this@LoginActivity).save(string)
                    }
                    else -> shortToast(bean.errorMsg)
                }
            }

            override fun error(error: String) {
                println("URL_LOGIN $error")
            }
        }).post(API.URL_LOGIN, map)
    }

    override fun setLayoutId(): Int = R.layout.activity_login
}