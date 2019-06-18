package android.wan.com.wanandroid.ui.fragment

import android.os.Bundle
import android.preference.PreferenceFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.wan.com.wanandroid.R

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/1$ 1:59$.
 */
class SettingFragment : PreferenceFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        addPreferencesFromResource(R.xml.setting_prefernce)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}