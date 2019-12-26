package com.gavindon.mvvm_kotlin.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.base.ViewModelProviders
import com.gavindon.mvvm_lib.base.my_interface.IView
import com.gavindon.mvvm_lib.net.EmptySource
import com.gavindon.mvvm_lib.net.ErrorSource
import com.gavindon.mvvm_lib.net.Resource
import com.gavindon.mvvm_lib.net.SuccessSource
import com.gavindon.mvvm_lib.utils.onFailed
import com.gavindon.mvvm_lib.utils.onSuccessT

/**
 * description:
 * Created by liNan on  2019/12/17 14:09
 */
abstract class BaseActivity : AppCompatActivity(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onInit(savedInstanceState)
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun onInit(savedInstanceState: Bundle?)

    override fun initStateView() {

    }

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
                onSuccess(resource.body)
            }
            is EmptySource -> {
            }
            is ErrorSource -> {
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

}