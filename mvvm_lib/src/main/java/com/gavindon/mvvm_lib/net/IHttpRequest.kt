package com.gavindon.mvvm_lib.net

import com.gavindon.mvvm_lib.utils.Parameters
import com.gavindon.mvvm_lib.utils.onFailed
import com.gavindon.mvvm_lib.utils.onSuccess
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * description: http请求接口
 * 可以使用不同的框架来实现get、post等请求
 * 实现此接口来进行切换
 * Created by liNan on  2019/12/19 10:19
 */

interface IHttpRequest {
    companion object {
        val compositeDisposable: CompositeDisposable? = CompositeDisposable()
    }

    /**
     * get请求
     * @param url 请求地址
     * @param param 请求参数
     */
    fun get(url: String, param: Parameters? = null): Single<String>

    fun post(url: String, param: Parameters? = null, onSuccess: onSuccess, onFailed: onFailed)

    fun getWithoutLoading(
        url: String,
        param: Parameters? = null,
        onSuccess: onSuccess,
        onFailed: onFailed
    )

    fun getMerge() {
    }

}

interface IFuelHttp : IHttpRequest {
}





