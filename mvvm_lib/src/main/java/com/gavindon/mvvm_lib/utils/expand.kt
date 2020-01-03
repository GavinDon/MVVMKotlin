package com.gavindon.mvvm_lib.utils

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 * description:
 * Created by liNan on 2019/12/24 15:52

 */
inline fun <reified T> genericType(): Type = object : TypeToken<T>() {}.type





