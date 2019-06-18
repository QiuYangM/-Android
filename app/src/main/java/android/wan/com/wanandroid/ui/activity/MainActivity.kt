package android.wan.com.wanandroid.ui.activity

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.adpter.MainViewPagerAdpter
import android.wan.com.wanandroid.base.BaseActivity
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.navigation_top.view.*

class MainActivity : BaseActivity() {

    private val adpter by lazy {
        MainViewPagerAdpter(supportFragmentManager)
    }
    private val mTitle by lazy {
        arrayListOf("玩Android", "知识体系", "公众号", "项目")
    }

    override fun setLayoutId(): Int = R.layout.activity_main


    override fun initView() {
        setWindowBackG()
        setToolBar()
        setDrawerLayout()
        setAdpter()
    }

    /**
     * 设置 viewpager适配器
     */
    private fun setAdpter() {
        viewPager.adapter = adpter
        viewPager.offscreenPageLimit = 4
        setLisenter()
    }

    /**
     * toolbar
     */
    private fun setToolBar() {
        toolbar.run {
            title = getString(R.string.app_name)
            setSupportActionBar(this)
        }
    }

    /**
     * 抽屉监听
     */
    private fun setLisenter() {
        navigation.run {
            setNavigationItemSelectedListener(onDrawerNavigationItemSelectedListener)
            val view = getHeaderView(0)
            val textView = view.findViewById<TextView>(R.id.is_login_show)
            textView.setOnClickListener {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            }
        }
        buttomNavigation.run {
            setOnNavigationItemSelectedListener(onButtimNavigationListener)
        }
    }

    /**
     * drawerlayout
     */
    private fun setDrawerLayout() {
        /**
         *      设置打开关闭的动画
         * 上下文
         * drawerlayout
         * toolbar
         * 打开
         * 关闭
         */
        drawerLayout.run {
            val toggle = ActionBarDrawerToggle(
                    this@MainActivity,
                    this,
                    toolbar,
                    R.string.navigation_drawer_open,
                    R.string.navigation_drawer_close
            )
            addDrawerListener(toggle)
            toggle.syncState()
        }
    }

    /**
     * 底部导航栏点击监听
     */
    val onButtimNavigationListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home_item -> {
                viewPager.currentItem = 0
                toolbar.title = mTitle[0]
            }
            R.id.item_know -> {
                viewPager.currentItem = 1
                toolbar.title = mTitle[1]
            }
            R.id.item_tencent -> {
                viewPager.currentItem = 2
                toolbar.title = mTitle[2]
            }
            R.id.item_github -> {
                viewPager.currentItem = 3
                toolbar.title = mTitle[3]
            }
        }
        return@OnNavigationItemSelectedListener true
    }
    /**
     * 抽屉 menu菜单的点击监听
     */
    val onDrawerNavigationItemSelectedListener =
            NavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_setting -> {
                        Intent(this, SettingActivity::class.java).run {
                            startActivity(this)
                        }
                    }
                }
                return@OnNavigationItemSelectedListener true
            }

    /**
     * 设置toolbar的布局
     */
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * toolbar menu菜单的点击事件
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.main_search -> startActivity(Intent(this, SearchActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    /**
     *设置window背景
     */
    private fun setWindowBackG() {
        window.decorView.setBackgroundColor(getColor(R.color.white))
    }


}
