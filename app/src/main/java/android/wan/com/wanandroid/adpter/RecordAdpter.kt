package android.wan.com.wanandroid.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.bean.SearchRecordBean
import android.wan.com.wanandroid.utils.lisetener.ItemClickLisener
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_item_record.view.*

class RecordAdpter : RecyclerView.Adapter<RecordAdpter.VH>() {

    private var list: List<SearchRecordBean> = listOf()
    private var click: ItemClickLisener? = null

    fun RecordAdpter(click: ItemClickLisener?) {
        this.click = click
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.findViewById<TextView>(R.id.record_context)
            itemView.findViewById<CardView>(R.id.record_item_layout)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.layout_item_record, parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val bean = list[position]
        holder.itemView.record_context.text = bean.context
        holder.itemView.record_item_layout.setOnClickListener { click?.callBack(bean.context)}
    }

    fun setList(it: MutableList<SearchRecordBean>) {
        this.list = it
        notifyDataSetChanged()
    }

}
