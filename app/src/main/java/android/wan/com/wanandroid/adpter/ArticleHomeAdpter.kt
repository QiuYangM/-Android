package android.wan.com.wanandroid.adpter

import android.view.View
import android.view.ViewGroup
import android.wan.com.wanandroid.adpter.viewitem.ArticleItem
import android.wan.com.wanandroid.bean.DataX
import android.wan.com.wanandroid.net.API
import android.wan.com.wanandroid.net.HttpHelper
import android.wan.com.wanandroid.utils.lisetener.ItemClickLisener
import androidx.recyclerview.widget.RecyclerView

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/2$ 1:36$.
 */
class ArticleHomeAdpter : RecyclerView.Adapter<ArticleHomeAdpter.VH>() {

    private var mData: List<DataX> = listOf()

    private var itemClick: ItemClickLisener? = null

    fun ArticleHomeAdpter1(clickCallBack: ItemClickLisener) {
        this.itemClick = clickCallBack
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ArticleItem(parent.context))
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val vh = holder.itemView as ArticleItem
        val bean = mData[position]
        vh.setDataBean(bean, this, position)

        vh.getClick().setOnClickListener {
            itemClick?.callBack(bean)
        }
        vh.toCellok().setOnClickListener {

            toCollect(bean,position)
        }
    }

    /**
     * 请求收藏功能
     */
    private fun toCollect(bean: DataX, position: Int) {
        val map = HashMap<String, String>()
        map["title"] = bean.title
        map["author"] = bean.author
        map["link"] = bean.link
        HttpHelper().result(object : HttpHelper.HttpHelperCallBack {
            override fun success(string: String) {
                println("URL_TO_COLLECKT $string")

                when (bean.collect) {
                    true -> bean.collect = false
                    false -> bean.collect = true
                }
                notifyItemChanged(position)
            }

            override fun error(error: String) {
                println("URL_TO_COLLECKT $error")
            }
        }).post(API.URL_TO_COLLECKT, map)
    }

    fun setList(mData: List<DataX>) {
        this.mData = mData
        notifyDataSetChanged()
    }


    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

}