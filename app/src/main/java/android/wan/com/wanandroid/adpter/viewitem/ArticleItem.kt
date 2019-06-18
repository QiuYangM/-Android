package android.wan.com.wanandroid.adpter.viewitem

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.wan.com.wanandroid.R
import android.wan.com.wanandroid.adpter.ArticleHomeAdpter
import android.wan.com.wanandroid.bean.DataX
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.item_articke.view.*


/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/2$ 1:55$.
 */
class ArticleItem : LinearLayout {
    private var position: Int = 0
    private var articleHomeAdpter: ArticleHomeAdpter? = null
    private var dataX: DataX? = null

    fun setDataBean(dataX: DataX, articleHomeAdpter: ArticleHomeAdpter, position: Int) {
        this.dataX = dataX
        this.articleHomeAdpter = articleHomeAdpter
        this.position = position

        article_title.text = dataX.title
        article_author.text = dataX.author
        article_time.text = dataX.niceDate
        article_chapterName.text = "${dataX.superChapterName}·${dataX.chapterName}"
        if (dataX.collect) article_praise.setImageResource(R.drawable.ic_collect_y)
        else article_praise.setImageResource(R.drawable.ic_collect_n)
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.item_articke, this)
    }

    fun getClick() = all_top

    fun toCellok() = article_praise
}