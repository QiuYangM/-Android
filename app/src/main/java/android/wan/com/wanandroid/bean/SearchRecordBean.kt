package android.wan.com.wanandroid.bean

import org.litepal.crud.LitePalSupport

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/9$ 0:30$.
 */
data class SearchRecordBean (
        val context: String,
        val id: Long =0
):LitePalSupport()