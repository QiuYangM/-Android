package android.wan.com.wanandroid.bean

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/10$ 0:28$.
 */data class LoginBean(
    val `data`: LoginData,
    val errorCode: Int,
    val errorMsg: String
)

data class LoginData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)