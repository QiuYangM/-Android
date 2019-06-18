package android.wan.com.wanandroid.adpter

import android.wan.com.wanandroid.ui.fragment.HomeFragment
import android.wan.com.wanandroid.ui.fragment.KnowFragment
import android.wan.com.wanandroid.ui.fragment.ProjectFragment
import android.wan.com.wanandroid.ui.fragment.TencentFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/1$ 19:51$.
 */
class MainViewPagerAdpter : FragmentPagerAdapter {

    private val mFragmentList by lazy {
        arrayListOf(HomeFragment(), KnowFragment(), TencentFragment(), ProjectFragment())
    }

    constructor(fm: FragmentManager) : super(fm)

    override fun getItem(position: Int): Fragment = mFragmentList[position]

    override fun getCount(): Int = mFragmentList.size

}