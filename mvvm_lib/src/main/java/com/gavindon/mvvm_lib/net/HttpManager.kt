package com.gavindon.mvvm_lib.net

import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.interceptors.LogRequestAsCurlInterceptor
import com.github.kittinunf.fuel.core.interceptors.LogResponseInterceptor


/**
 * description: http config类
 * 提供http请求者
 * Created by liNan on  2019/12/19 10:28
 */
class HttpManager private constructor() {

    var baseUrl: String? = null
    var http: IHttpRequest? = null
    val headers = hashMapOf<String, String>()

    /**
     * 提供不同的框架使用
     * 不改变view的调用方式
     */
    fun initHttp(httpFrame: HttpFrame): HttpManager {
        http = HttpProvider.createHttp(httpFrame)
        return this
    }

    /**
     * 添加请求头
     */
    fun addHeader(pair: Pair<String, String>) {
        headers[pair.first] = pair.second
    }

    /**
     * 最后一步调用
     */
    fun build() {
        if (http is FuelHttp) {
            FuelManager.instance.apply {
                basePath = baseUrl
                baseHeaders = headers
                addRequestInterceptor(LogRequestAsCurlInterceptor)
                addResponseInterceptor(LogResponseInterceptor)
            }
        }
    }

    companion object {
        // 双重检测
        val instance: HttpManager by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
            HttpManager()
        }
    }

}

/**
 * 使用前在application中先初始化httpFrame
 */
val http = HttpManager.instance.http

enum class HttpFrame {
    FUEL, RETROFIT
}