package com.gavindon.mvvm_lib.net

/**
 * description:
 * Created by liNan on  2019/12/18 16:56
 */


/**
 * 只返回成功的数据
 */
interface ISingleRequestCallBack<T : Any> {
    fun onSuccess(data: Resource<T>)
}

interface IRequestCallBack<T : Any> : ISingleRequestCallBack<T> {
    fun onFaild(error: Throwable)
}

