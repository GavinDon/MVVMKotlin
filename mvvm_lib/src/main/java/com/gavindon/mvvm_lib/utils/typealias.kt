package com.gavindon.mvvm_lib.utils

/**
 * description:
 * Created by liNan on 2019/12/24 15:47

 */
typealias Parameters = List<Pair<String, Any?>>

typealias onSuccess = (String) -> Unit
typealias onFailed = (Throwable) -> Unit
typealias onSuccessT<T> = (T) -> Unit
