package com.gavindon.mvvm_kotlin.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * description:
 * Created by liNan on  2019/12/23 17:01
 */
class GsonUtil {

    companion object {
        fun <T : Any> fromJson(json: String): T {
            return Gson().fromJson<T>(json, object : TypeToken<T>() {}.type)
        }
    }

}