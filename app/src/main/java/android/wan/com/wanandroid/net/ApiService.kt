package android.wan.com.wanandroid.net

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.*

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/1$ 22:59$.
 */
interface ApiService {

    @GET
    operator fun get(@Url url: String): Observable<ResponseBody>

    @POST
    fun post(@Url url: String, @QueryMap map: Map<String, String>): Observable<ResponseBody>

    /**
     * 首页分页加载
     */
    @GET("article/list/{page}/json")
    fun getArticle(@Url url: String, @Path("0") page: String)

}