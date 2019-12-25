package com.gavindon.mvvm_lib.net

import com.gavindon.mvvm_lib.utils.Parameters
import com.gavindon.mvvm_lib.utils.onFailed
import com.gavindon.mvvm_lib.utils.onSuccess
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.rx.rxResponseString
import io.reactivex.disposables.CompositeDisposable


/**
 * description:
 * Created by liNan on  2019/12/19 10:27
 */
class FuelHttp private constructor() : IFuelHttp {


    override val compositeDisposable: CompositeDisposable? = CompositeDisposable()

    companion object {
        val instance: FuelHttp by lazy(LazyThreadSafetyMode.PUBLICATION) { FuelHttp() }
    }

    override fun get(
        url: String,
        param: Parameters?,
        onSuccess: onSuccess,
        onFailed: onFailed
    ) {
        val get = url.httpGet(param)
            .rxResponseString()
            .compose(RxScheduler.applySingleScheduler())
            .subscribe({
                onSuccess(it)
            }, {
                onFailed(it)
            })
        compositeDisposable?.add(get)
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
        compositeDisposable?.add(post)
    }


}