package com.gavindon.mvvm_lib.net

import com.gavindon.mvvm_lib.utils.Parameters
import com.gavindon.mvvm_lib.utils.onFailed
import com.gavindon.mvvm_lib.utils.onSuccess
import com.gavindon.mvvm_lib.utils.onSuccessT
import com.github.kittinunf.fuel.core.HttpException
import com.github.kittinunf.fuel.gson.gsonDeserializer
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.rx.rxResponseObject
import com.github.kittinunf.fuel.rx.rxResponseString
import com.google.gson.Gson
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
    crossinline onSuccess: onSuccessT<T>
) {
    IHttpRequest.compositeDisposable?.add(this.subscribe({
        val a = Gson().fromJson<T>(it, type)
        onSuccess.invoke(a)
    }, {
        if (it is HttpException) {
        }
    }))
}


