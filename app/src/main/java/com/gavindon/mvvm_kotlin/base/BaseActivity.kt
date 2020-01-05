package com.gavindon.mvvm_kotlin.base

import android.content.Context
import android.util.Log
import com.gavindon.mvvm_lib.base.BindContextViewModelFactory
import com.gavindon.mvvm_lib.base.MVVMBaseActivity
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.base.ViewModelProviders
import com.gavindon.mvvm_lib.net.*
import com.gavindon.mvvm_lib.utils.onFailed
import com.gavindon.mvvm_lib.utils.onSuccessT

/**
 * description:
 * Created by liNan on  2019/12/17 14:09
 */
abstract class BaseActivity : MVVMBaseActivity() {

    /**
     * @T 只代表data 不包含code message
     */
    inline fun <reified T> handlerResponseData(
        resource: Resource<T>,
        onSuccess: onSuccessT<T>,
        onFailed: onFailed
    ) {
        when (resource) {
            is SuccessSource -> {
                onSuccess.invoke(resource.body)
                Log.i("hahah", resource.body.toString())
            }
            is ErrorSource -> {
                Log.i("hahah", resource.e.localizedMessage)
            }
            is CodeNotZero -> {
                Log.i("hahah", (resource.msg))

            }
        }
    }

    /**
     * 在java用泛型时不能够直接地使用类型。
     * 而在kotlin中使用inline配合reified可以使用泛型类型
     */
    inline fun <reified V : MVVMBaseViewModel> getViewModel(): V {
        return ViewModelProviders.of(this).get(V::class.java)
    }

    /**
     * 带有context的viewModel
     */
    inline fun <reified V : MVVMBaseViewModel> getViewModel(context: Context): V {
        val factory = BindContextViewModelFactory.getInstance(context)
        return ViewModelProviders.of(this, factory).get(V::class.java)
    }

}