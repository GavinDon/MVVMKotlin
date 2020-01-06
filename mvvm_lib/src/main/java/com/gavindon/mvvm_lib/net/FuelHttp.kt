package com.gavindon.mvvm_lib.net

import com.gavindon.mvvm_lib.utils.*
import com.github.kittinunf.fuel.gson.gsonDeserializer
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.rx.rxResponseObject
import com.github.kittinunf.fuel.rx.rxResponseString
import com.google.gson.JsonSyntaxException
import com.orhanobut.logger.Logger
import io.reactivex.Single
import java.lang.reflect.Type


/**
 * description:
 * Created by liNan on  2019/12/19 10:27
 */
class FuelHttp private constructor() : IFuelHttp {


    companion object {
        val instance: FuelHttp by lazy(LazyThreadSafetyMode.PUBLICATION) { FuelHttp() }
    }

    override fun get(url: String, param: Parameters?): Single<String> {
        return url.httpGet(param)
            .rxResponseString()
            .compose(RxScheduler.applySingleScheduler())
    }


    override fun post(url: String, param: Parameters?, onSuccess: onSuccess, onFailed: onFailed) {
        val post = url.httpPost(param)
            .rxResponseString()
            .compose(RxScheduler.applySingleScheduler())
            .subscribe({
                onSuccess(it)
            }, {
                onFailed(it)
            })
    }


    override fun getWithoutLoading(
        url: String,
        param: Parameters?,
        onSuccess: onSuccess,
        onFailed: onFailed
    ) {
        val get = url.httpGet(param)
            .rxResponseObject(gsonDeserializer<BaseResponse<String>>())
            .compose(RxScheduler.applySingleScheduler())
            .subscribe({

            }, {
            })
    }


}

inline fun <reified T> Single<String>.parse(
    type: Type,
    crossinline onSuccess: onSuccessT<T>,
    crossinline onFailed: onFailed
) {
    IHttpRequest.compositeDisposable?.add(this.subscribe({
        val p = GsonUtil.str2Obj<T>(it, type)
        if (null != p) {
            //反参解析正常
            onSuccess(p)
        } else {
            //如果返回解析数据为null则代表json解析异常
            onFailed(JsonSyntaxException("json异常"))
        }
        Logger.json(it)

    }, {
        Logger.e(it, "网络请求")
        onFailed(it)
    }))
}



