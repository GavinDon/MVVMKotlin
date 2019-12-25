package com.gavindon.mvvm_lib.base

import androidx.lifecycle.ViewModel
import com.gavindon.mvvm_lib.net.http

/**
 * description:
 * Created by liNan on  2019/12/17 14:24
 */
abstract class MVVMBaseViewModel : ViewModel() {


    override fun onCleared() {
        super.onCleared()
        http?.compositeDisposable?.clear()
    }
}