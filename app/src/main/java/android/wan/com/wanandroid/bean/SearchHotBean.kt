package android.wan.com.wanandroid.bean

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/8$ 17:19$.
 */
data class SearchHotBean(

    val `data`: List<HotData>,
    val errorCode: Int,
    val errorMsg: String
)

data class HotData(
    val id: Int,
    val link: String,
    val name: String,
    val order: Int,
    val visible: Int
)