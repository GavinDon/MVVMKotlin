package com.gavindon.mvvm_lib.net

import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * description:
 * Created by liNan on  2019/12/19 11:26
 */
class RxScheduler {

    companion object {
        fun <T> applySingleScheduler(): SingleTransformer<T, T> {
            return SingleTransformer {
                it.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            }
        }


    }
}