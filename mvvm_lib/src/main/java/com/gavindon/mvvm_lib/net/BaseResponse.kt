package com.gavindon.mvvm_lib.net

/**
 * description:
 * Created by liNan on  2019/12/19 10:14
 */
data class BaseResponse<T>(val code: Int, val msg: String, val data: T)

/**
 * viewModel返回到View的数据
 */


data class LiveData2View<T>(
    val data: Resource<BaseResponse<T>>? = null,
    val ex: Resource<Throwable>? = null
)