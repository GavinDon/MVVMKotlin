package com.gavindon.mvvm_lib.net

/**
 * description:
 * Created by liNan on  2019/12/19 10:14
 */
data class BaseResponse<T>(val code: Int, val msg: String, val data: T)
