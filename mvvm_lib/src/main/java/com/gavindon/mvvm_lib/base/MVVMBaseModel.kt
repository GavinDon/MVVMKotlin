package com.gavindon.mvvm_lib.base

import com.gavindon.mvvm_lib.base.my_interface.IModel
import com.gavindon.mvvm_lib.utils.GsonUtil
import com.google.gson.JsonSyntaxException
import org.json.JSONException
import java.lang.reflect.Type

/**
 * description:
 * Created by liNan on  2019/12/17 17:10
 */
abstract class MVVMBaseModel : IModel {

    fun <T> deserialize(strJson: String, type: Type): T? {
        return try {
            GsonUtil.str2Obj<T>(strJson, type)
        } catch (ex: JsonSyntaxException) {
            null
        } catch (ex: JSONException) {
            null
        }
    }

}