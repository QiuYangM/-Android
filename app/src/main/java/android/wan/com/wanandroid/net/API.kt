package android.wan.com.wanandroid.net

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/1$ 23:19$.
 */
object API {

    /**
     *域名
     */
    val URL = "https://www.wanandroid.com/"
    /**
     * 登录
     */
    val URL_LOGIN = "user/login"
    /**
     * 注册
     */
    val URL_REGIST = "user/register"

    /**首页相关*/
    //首页文章列表
    val URL_ARTICLE = "article/list/0/json"

    /**
     * 首页轮播图
     */
    val URL_BANNER = "banner/json"

    /***热门搜索**/
    val URL_SEARCH_HOT = "hotkey/json"
    /**
     * 搜索
     */
    val URL_TO_SEARCH = "article/query/0/json"
    /**
     * 收藏文章
     */
    val URL_TO_COLLECKT = "lg/collect/add/json"
}