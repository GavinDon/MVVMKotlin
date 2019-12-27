package com.gavindon.mvvm_lib.utils

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * description:
 * Created by liNan on 2019/12/24 15:52

 */
inline fun <reified T> genericType() = object : TypeToken<T>() {}.type

fun <T> deserialize(strJson: String, type: Type): T? {
    return try {
        Gson().fromJson(strJson, type)
    } catch (ex: JsonSyntaxException) {
        null
    }
}




