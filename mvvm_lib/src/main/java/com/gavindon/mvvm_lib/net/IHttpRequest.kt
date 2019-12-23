package com.gavindon.mvvm_lib.net

import io.reactivex.disposables.CompositeDisposable

/**
 * description: http请求接口
 * 可以使用不同的框架来实现get、post等请求
 * 实现此接口来进行切换
 * Created by liNan on  2019/12/19 10:19
 */
typealias Parameters = List<Pair<String, Any?>>


interface IHttpRequest {

    val compositeDisposable: CompositeDisposable?
    /**
     * get请求
     * @param url 请求地址
     * @param param 请求参数
     */
    fun <T : Any> get(url: String, param: Parameters? = null, onCallBack: ISingleRequestCallBack<T>)


}

interface IFuelHttp : IHttpRequest {}





