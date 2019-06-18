package android.wan.com.wanandroid.ui.activity

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.base.BaseActivity
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_details.*

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/4$ 15:02$.
 */
class H5DetilaActivity : BaseActivity() {
    private var url: String? = ""


    override fun initView() {
        val title = intent.getStringExtra("h5_title")
        url = intent.getStringExtra("h5_context")
        setSupportActionBar(h5Toobar)
        supportActionBar?.title = null
        title_h5.text = title

        mWebView.webViewClient = mWebviewClint
        mWebView.webChromeClient = mWebChore
        mWebView.loadUrl(url)
    }


    override fun setLayoutId(): Int = R.layout.activity_details

    /**
     * 设置菜单
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.h5_tool_menu, menu)
        return true
    }

    /**
     * 菜单点击
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
            R.id.click_load_more -> {
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private val mWebviewClint = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view,url)
        }
    }
    private val mWebChore = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            mProgress.progress = newProgress
            if (newProgress == 100) mProgress.visibility = View.GONE
        }
    }
}