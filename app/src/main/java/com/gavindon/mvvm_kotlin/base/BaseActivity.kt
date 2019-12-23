package com.gavindon.mvvm_kotlin.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.base.ViewModelProviders
import com.gavindon.mvvm_lib.base.my_interface.IView

/**
 * description:
 * Created by liNan on  2019/12/17 14:09
 */
abstract class BaseActivity : AppCompatActivity(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun initData() {
    }

    /**
     * 在java用泛型时不能够直接地使用类型。
     * 而在kotlin中使用inline配合reified可以使用泛型类型
     */
    inline fun <reified V : MVVMBaseViewModel> getViewModel(): V {
        val viewModel = ViewModelProviders.of(this).get(V::class.java)
        return viewModel
    }

}