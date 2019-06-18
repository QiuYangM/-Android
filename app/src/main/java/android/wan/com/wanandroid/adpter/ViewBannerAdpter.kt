package android.wan.com.wanandroid.adpter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.youth.banner.loader.ImageLoader

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/3$ 12:00$.
 */
class ViewBannerAdpter : ImageLoader() {

    override fun displayImage(context: Context, path: Any?, imageView: ImageView) {
        Glide.with(context).load(path).into(imageView)
    }


}