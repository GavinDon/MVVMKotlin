package com.gavindon.mvvm_kotlin.repository

import android.util.Log
import com.gavindon.mvvm_kotlin.ApiService
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_lib.base.MVVMBaseModel
import com.gavindon.mvvm_lib.net.BaseResponse
import com.gavindon.mvvm_lib.net.http
import com.gavindon.mvvm_lib.net.parse
import com.gavindon.mvvm_lib.utils.Parameters
import com.gavindon.mvvm_lib.utils.genericType

/**
 * description:
 * Created by liNan on  2019/12/23 16:52
 */
class MainRepository : MVVMBaseModel() {

    fun getBanner(
        param: Parameters
    ) {
        val t = genericType<BaseResponse<LoginResp>>()
        http?.get(ApiService.url_login, param)
            ?.parse<BaseResponse<LoginResp>>(t) {
                Log.i("hahah", it.data.name)
            }
    }


}