package com.gavindon.mvvm_lib.utils

import com.gavindon.mvvm_lib.net.AppException
import com.gavindon.mvvm_lib.net.ErrorSource
import com.gavindon.mvvm_lib.net.Resource
import com.gavindon.mvvm_lib.net.SuccessSource
import com.github.kittinunf.fuel.core.HttpException

/**
 * description:
 * Created by liNan on 2019/12/24 15:47

 */
typealias Parameters = List<Pair<String, Any?>>

typealias onSuccess = (String) -> Unit
typealias onSuccessResource<T> = (Resource<T>) -> Unit
typealias onFailed = (Throwable) -> Unit
typealias onFaileds = (ErrorSource<Throwable?>) -> Unit
typealias onSuccessT<T> = (T) -> Unit
typealias onSuccessAny = (Any) -> Unit


