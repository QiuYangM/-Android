package android.wan.com.wanandroid.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.bean.HotData
import android.wan.com.wanandroid.utils.lisetener.ItemClickLisener
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_hot_item.view.*

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/8$ 17:44$.
 */

class HotSearchAdpter : RecyclerView.Adapter<HotSearchAdpter.VH>() {
    private var list: List<HotData> = listOf()
    private var callBack: ItemClickLisener? = null

    fun HotSearchAdpter(callBack: ItemClickLisener) {
        this.callBack = callBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.layout_hot_item, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val dataBean = list[position]
        holder.itemView.hot_name.text = dataBean.name
        holder.itemView.hot_item_layout.setOnClickListener { callBack?.callBack(dataBean) }
    }

    fun setList(it: List<HotData>) {
        this.list = it
        notifyDataSetChanged()
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.findViewById<TextView>(R.id.hot_name)
            itemView.findViewById<CardView>(R.id.hot_item_layout)
        }
    }
}