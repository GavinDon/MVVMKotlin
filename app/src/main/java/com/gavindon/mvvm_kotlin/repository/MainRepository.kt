package com.gavindon.mvvm_kotlin.repository

import com.gavindon.mvvm_kotlin.ApiService
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_lib.base.MVVMBaseModel
import com.gavindon.mvvm_lib.net.BaseResponse
import com.gavindon.mvvm_lib.net.Resource
import com.gavindon.mvvm_lib.net.http
import com.gavindon.mvvm_lib.utils.Parameters
import com.gavindon.mvvm_lib.utils.deserialize
import com.google.gson.reflect.TypeToken

/**
 * description:
 * Created by liNan on  2019/12/23 16:52
 */
class MainRepository : MVVMBaseModel() {

    fun getBanner(
        param: Parameters,
        onSuccess: (Resource<LoginResp>) -> Unit

    ) {
        http?.get(ApiService.url_login, param, {
            val resp = deserialize<BaseResponse<LoginResp>>(
                it,
                object : TypeToken<BaseResponse<LoginResp>>() {}.type
            )
            onSuccess.invoke(Resource.create(resp))
        }, {
            Resource.create(it)
        })
    }


}