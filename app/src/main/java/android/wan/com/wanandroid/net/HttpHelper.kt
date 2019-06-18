package android.wan.com.wanandroid.net

import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.*
import kotlin.collections.HashMap as HashMap1

/**
 * @type  $method$
 * @explain $EXPLAIN$
 * @author owner$.
 * @creat time 2019/6/1$ 22:59$.
 */
class HttpHelper {

    private var apiService: ApiService
    private var httpCallBack: HttpHelperCallBack? = null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(API.URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    /**
     * get请求
     */
    fun get(url: String) {

        apiService.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * post请求
     */
    fun post(url: String, map: HashMap<String, String>) {
        apiService.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * rxjava 异步监听
     */
    private val observer = object : Observer<ResponseBody> {
        override fun onSubscribe(d: Disposable) {
        }

        override fun onNext(responseBody: ResponseBody) {

            httpCallBack?.success(responseBody.string())
        }

        override fun onError(e: Throwable) {
            httpCallBack?.error(e.toString())
        }

        override fun onComplete() {

        }
    }

    fun result(callBack: HttpHelperCallBack): HttpHelper {
        this.httpCallBack = callBack
        return this
    }


    interface HttpHelperCallBack {
        fun success(string: String)
        fun error(error: String)
    }
}