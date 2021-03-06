package com.gavindon.mvvm_kotlin.viewmodel

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_kotlin.repository.MainRepository
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.net.Resource
import com.gavindon.mvvm_lib.utils.Parameters
import com.gavindon.mvvm_lib.utils.singLiveData

/**
 * description:
 * Created by liNan on  2019/12/17 14:28
 */
class MainViewModel : MVVMBaseViewModel(), LifecycleOwner {

    private val mainRepository: MainRepository = MainRepository()
    private val lifecycleRegistry = LifecycleRegistry(this)

    fun getLogin(reqParam: Parameters): singLiveData<LoginResp> {
        val liveData = singLiveData<LoginResp>()
        mainRepository.getBanner(reqParam, {
            liveData.value = Resource.create(it)
        }, {
            liveData.value = Resource.create(it)
        }
        )
        return liveData
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}

