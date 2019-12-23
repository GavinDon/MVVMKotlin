package com.gavindon.mvvm_lib.net

import android.annotation.SuppressLint
import com.github.kittinunf.fuel.gson.gsonDeserializer
import com.github.kittinunf.fuel.gson.gsonDeserializerOf
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.rx.rxResponseObject
import com.github.kittinunf.fuel.rx.rxResponseString
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiConsumer
import java.util.*


/**
 * description:
 * Created by liNan on  2019/12/19 10:27
 */
class FuelHttp private constructor() : IFuelHttp {

    override val compositeDisposable: CompositeDisposable? = CompositeDisposable()

    override fun <T : Any> get(url: String, param: Parameters?, onCallBack: ISingleRequestCallBack<T>) {
        val get = url.httpGet(param)
            .rxResponseObject(gsonDeserializer<BaseResponse<T>>())
            .compose(RxScheduler.applySingleScheduler())
            .subscribe({
                onCallBack.onSuccess(Resource.create(it))
            }, {
                Resource.create(it)
            })
        compositeDisposable?.add(get)
    }


    companion object {
        val instance: FuelHttp by lazy { FuelHttp() }

    }


}