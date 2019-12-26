package com.gavindon.mvvm_kotlin.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.base.ViewModelProviders
import com.gavindon.mvvm_lib.base.my_interface.IView

/**
 * description:
 * Created by liNan on  2019/12/17 14:09
 */
abstract class BaseFragment : Fragment(), IView {

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(layoutId, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onInit(savedInstanceState)
    }

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected abstract fun onInit(savedInstanceState: Bundle?)

    override fun initStateView() {

    }

    /**
     * 在java用泛型时不能够直接地使用类型。
     * 而在kotlin中使用inline配合reified可以使用泛型类型
     */
    inline fun <reified V : MVVMBaseViewModel> getViewModel(): V {
        return ViewModelProviders.of(this).get(V::class.java)
    }

}