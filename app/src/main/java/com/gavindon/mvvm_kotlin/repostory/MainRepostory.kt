package com.gavindon.mvvm_kotlin.repostory

import com.gavindon.mvvm_kotlin.ApiService
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_kotlin.bean.NoticeResp
import com.gavindon.mvvm_kotlin.utils.reqParams
import com.gavindon.mvvm_lib.base.MVVMBaseModel
import com.gavindon.mvvm_lib.net.ISingleRequestCallBack
import com.gavindon.mvvm_lib.net.Resource
import com.gavindon.mvvm_lib.net.SuccessSource
import com.gavindon.mvvm_lib.net.http
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import com.google.gson.reflect.TypeToken

/**
 * description:
 * Created by liNan on  2019/12/23 16:52
 */
class MainRepostory : MVVMBaseModel() {

    fun getBanner(param: reqParams) {
        http?.get(
            ApiService.url_login,
            param,
            object : ISingleRequestCallBack<LinkedTreeMap<String, Any>> {
                override fun onSuccess(data: Resource<LinkedTreeMap<String, Any>>) {
                    if (data is SuccessSource) {
                        val b = Gson().toJson(data.body)
                        val a = Gson().fromJson(b, LoginResp::class.java)
                        val e = a.email
                    }
                }


            })

    }

    fun getBanner2() {
        http?.get(
            ApiService.url_notice,
            onCallBack = object : ISingleRequestCallBack<LinkedTreeMap<String, Any>> {
                override fun onSuccess(data: Resource<LinkedTreeMap<String, Any>>) {
                    if (data is SuccessSource) {
                        val b = Gson().toJson(data.body)
                        val a = Gson().fromJson<List<NoticeResp>>(b, object : TypeToken<List<NoticeResp>>() {}.type)
                    }
                }


            })

    }
}