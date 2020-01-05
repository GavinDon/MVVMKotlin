package com.gavindon.mvvm_kotlin.repository

import com.gavindon.mvvm_kotlin.ApiService
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_lib.base.MVVMBaseModel
import com.gavindon.mvvm_lib.net.*
import com.gavindon.mvvm_lib.utils.*
import com.google.gson.reflect.TypeToken

/**
 * description:
 * Created by liNan on  2019/12/23 16:52
 */
class MainRepository : MVVMBaseModel() {


    fun getBanner(
        param: List<Pair<String, Any?>>,
        function: (LoginResp?) -> Unit,
        onFailed: onFailed
    ) {
        val t = object : TypeToken<BaseResponse<LoginResp>>() {}.type
        http?.get(ApiService.url_login, param)
            ?.parse<BaseResponse<LoginResp>>(t, { resource ->
                function(resource.data)
            }, {
                onFailed(it)
            })
    }

}
