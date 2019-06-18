package android.wan.com.wanandroid.ui.activity


import android.Manifest
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.adpter.ArticleHomeAdpter
import android.wan.com.wanandroid.adpter.HotSearchAdpter
import android.wan.com.wanandroid.adpter.RecordAdpter
import android.wan.com.wanandroid.base.BaseActivity
import android.wan.com.wanandroid.bean.*
import android.wan.com.wanandroid.net.API
import android.wan.com.wanandroid.net.HttpHelper
import android.wan.com.wanandroid.ui.fragment.HomeFragment
import android.wan.com.wanandroid.utils.lisetener.ItemClickLisener
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.gson.Gson
import com.joker.api.Permissions4M
import com.joker.api.wrapper.ListenerWrapper
import kotlinx.android.synthetic.main.activity_search.*
import org.litepal.LitePal

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/8$ 16:45$.
 */
class SearchActivity : BaseActivity(), TextView.OnEditorActionListener, ItemClickLisener {

    override fun <B : Any> callBack(bean: B) {
        val context = bean as HotData
        toSearch(context.name)
    }


    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
            val context = v?.text.toString().trim()
            var s = ""
            s = if ("" != context) {
                context
            } else {
                v?.hint.toString()
            }
            val bean = SearchRecordBean(s)
            bean.save()
            //搜索
            toSearch(s)
            return true
        }
        return false
    }

    /**
     * 请求搜索
     */
    private fun toSearch(keyWords: String) {
        val map = HashMap<String, String>()
        map["k"] = keyWords
        HttpHelper().result(object : HttpHelper.HttpHelperCallBack {
            override fun success(string: String) {
                println("URL_TO_SEARCH$string")
                val data = Gson().fromJson(string, ArticleBean::class.java)
                data.data.let {
                    when (it.size) {
                        0 -> return
                        else -> {
                            search_EditText.hint = keyWords
                            is_show_layout.visibility = View.GONE
                            show_search_rcl.visibility = View.VISIBLE

                            show_search_rcl.adapter = articleAdpter
                            show_search_rcl.layoutManager = LinearLayoutManager(this@SearchActivity)
                            articleAdpter.setList(it.datas)
                        }
                    }

                }
            }

            override fun error(error: String) {
                println("URL_TO_SEARCH$error")
            }
        }).post(API.URL_TO_SEARCH, map)
    }

    /**
     * 适配器
     */
    private val adpterHot by lazy {
        HotSearchAdpter()
    }
    private val adpterRecord by lazy {
        RecordAdpter()
    }
    private val articleAdpter by lazy {
        ArticleHomeAdpter()
    }

    override fun initView() {
        setSupportActionBar(Search_tootBar)
        Search_tootBar.setNavigationOnClickListener {
            finish()
        }

        search_EditText.setOnEditorActionListener(this)
        /**热门搜索*/
        val manner = FlexboxLayoutManager(this)
        //设置主轴排列方式
        manner.flexDirection = FlexDirection.ROW
        //设置是否换行
        manner.flexWrap = FlexWrap.WRAP

        manner.alignItems = AlignItems.STRETCH

        hot_recyclerview.layoutManager = manner

        hot_recyclerview.adapter = adpterHot
        /**搜索记录*/
        RecordRecy.layoutManager = LinearLayoutManager(this)

        RecordRecy.adapter = adpterRecord
        //热门搜索
        doHttp()
        //getSQL
        getSQL()
        //清除按钮
        clear_record_data.setOnClickListener {
            LitePal.deleteAll(SearchRecordBean::class.java)
            getSQL()
        }
        /**item接口回调*/
        adpterHot.HotSearchAdpter(this)
        adpterRecord.RecordAdpter(object : ItemClickLisener {
            override fun <B : Any> callBack(bean: B) {
                toSearch(bean as String)
            }
        })
        articleAdpter.ArticleHomeAdpter1(object :ItemClickLisener{
            override fun <B : Any> callBack(bean: B) {
                val beans = bean as DataX
                setAction(beans.title,beans.link)
            }
        })
    }

    /**
     * 动态权限
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * 拿到数据库的数据
     */
    private fun getSQL() {
        Permissions4M.get(this)
                // 是否强制弹出权限申请对话框，建议为 true，默认为 true
                .requestForce(true)
                // 是否支持5.0权限申请 默认false
                .requestUnderM(false)
                // 权限
                .requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                // 权限申请码
                .requestCodes(1)
                // 权限请求结果
                .requestListener(object : ListenerWrapper.PermissionRequestListener {

                    override fun permissionGranted(code: Int) {
                        when (code) {
                            1 -> {
                                val data = LitePal.findAll(SearchRecordBean::class.java)
                                if (data.size > 0) {
                                    clear_record_data.visibility = View.VISIBLE
                                } else {
                                    clear_record_data.visibility = View.GONE
                                }
                                data.let(toRefresh())
                            }

                        }
                    }

                    override fun permissionDenied(code: Int) {
                        when (code) {
                            1 -> Toast.makeText(this@SearchActivity, "相机权限授权失败", Toast.LENGTH_SHORT).show()
                        }
                    }

                    override fun permissionRationale(code: Int) {
                        when (code) {
                            1 -> Toast.makeText(this@SearchActivity, "请开启相机权限", Toast.LENGTH_SHORT).show()
                        }
                    }
                })
                // 权限完全被禁时回调函数中返回 intent 类型（系统设置界面）
                .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                // 权限完全被禁时回调，接口函数中的参数 Intent 是由上一行决定的
                .requestPage { _, p1 ->
                    AlertDialog.Builder(this)
                            .setMessage("相机权限申请：\n我们需要开启照相机的权限")
                            .setPositiveButton("确定") { _, _ -> this.startActivity(p1) }
                            .setNegativeButton("取消") { dialog, _ -> dialog.cancel() }
                            .show()
                }
                .request()
    }


    private fun toRefresh(): (MutableList<SearchRecordBean>) -> Unit {
        return {
            adpterRecord.setList(it)
        }
    }


    /**
     * 搜索热词
     */
    private fun doHttp() {
        HttpHelper().result(object : HttpHelper.HttpHelperCallBack {
            override fun success(string: String) {
                val bean = Gson().fromJson(string, SearchHotBean::class.java)
                bean.data.let {
                    adpterHot.setList(it)
                }
            }

            override fun error(error: String) {
                println("URL_SEARCH_HOT: $error")
            }

        }).get(API.URL_SEARCH_HOT)
    }

    override fun setLayoutId(): Int = R.layout.activity_search

    /**
     * 设置意图
     */
    private fun setAction(title: String, url: String) {
        val intent = Intent(this, H5DetilaActivity::class.java)
        intent.putExtra("h5_title", title)
        intent.putExtra("h5_context", url)
        startActivity(intent)
    }
}