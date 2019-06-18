package android.wan.com.wanandroid.ui.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.adpter.ArticleHomeAdpter
import android.wan.com.wanandroid.adpter.ViewBannerAdpter
import android.wan.com.wanandroid.base.BaseFragment
import android.wan.com.wanandroid.bean.ArticleBean
import android.wan.com.wanandroid.bean.BannerBean
import android.wan.com.wanandroid.bean.BannerData
import android.wan.com.wanandroid.bean.DataX
import android.wan.com.wanandroid.custom.CustomLayoutManager
import android.wan.com.wanandroid.net.API
import android.wan.com.wanandroid.net.HttpHelper
import android.wan.com.wanandroid.ui.activity.H5DetilaActivity
import android.wan.com.wanandroid.utils.lisetener.ItemClickLisener
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.ArrayList


class HomeFragment : BaseFragment() {
    val H5_TITLE = "h5_title"
    val H5_CONTEXT = "h5_context"

    override val getLayoutId: Int = R.layout.fragment_home

    /**
     * 文章适配器
     */
    private val adpter by lazy {
        ArticleHomeAdpter()
    }

    @SuppressLint("WrongConstant")
    override fun initView() {
        homeSwipe.isRefreshing = true
        homeSwipe.setOnRefreshListener {
            setArticData
            homeSwipe.isRefreshing = false
        }
        val manager = CustomLayoutManager(activity)
        manager.setScroolEnable(false)
        manager.orientation = LinearLayoutManager.VERTICAL
        homeRecyclerview.layoutManager = manager
        homeRecyclerview.adapter = adpter
        adpter.ArticleHomeAdpter1(itemliner)

        setArticData
        bannerData
    }

    /**
     * 文章列表 数据
     */
    private val setArticData = HttpHelper().result(object : HttpHelper.HttpHelperCallBack {
        override fun success(string: String) {
            val dataBean = Gson().fromJson(string, ArticleBean::class.java)
            val list = dataBean.data.datas
            list.let {
                adpter.setList(list)
            }
            homeSwipe.isRefreshing = false
        }

        override fun error(error: String) {
            println("initHepererror$error")
        }
    }).get(API.URL_ARTICLE)

    /**
     * 轮播 数据
     */
    private val bannerData = HttpHelper().result(object : HttpHelper.HttpHelperCallBack {
        override fun success(string: String) {
            val data = Gson().fromJson(string, BannerBean::class.java)
            data.data.let {
                homeViewPager.setImageLoader(ViewBannerAdpter())
                homeViewPager.setImages(getImageList(data.data))
                homeViewPager.start()
                homeViewPager.setOnBannerListener { position ->
                    val bannerDetila = it[position]
                    setAction(bannerDetila.title, bannerDetila.url)
                }
            }
        }

        override fun error(error: String) {
        }
    }).get(API.URL_BANNER)

    /**
     * 设置意图
     */
    private fun setAction(title: String, url: String) {
        val intent = Intent(activity, H5DetilaActivity::class.java)
        intent.putExtra(H5_TITLE, title)
        intent.putExtra(H5_CONTEXT, url)
        startActivity(intent)
    }

    /**
     * 返回图片集合
     */
    private fun getImageList(list: List<BannerData>): ArrayList<String> {
        val urlList = ArrayList<String>()
        list.forEach {
            urlList.add(it.imagePath)
        }
        return urlList
    }

    /**
     * item 点击事件
     */
    private val itemliner = object : ItemClickLisener {
        override fun <B : Any> callBack(bean: B) {
            val data = bean as DataX
            setAction(data.title, data.link)
        }

    }
}
