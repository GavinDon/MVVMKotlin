package com.gavindon.mvvm_kotlin.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_kotlin.repository.MainRepository
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.net.Resource
import com.gavindon.mvvm_lib.utils.Parameters

/**
 * description:
 * Created by liNan on  2019/12/17 14:28
 */
class MainViewModel(private val context: Context) : MVVMBaseViewModel() {

    private val mainRepository: MainRepository = MainRepository()

    fun getLogin(reqParam: Parameters): MutableLiveData<Resource<LoginResp>> {
        val liveData = MutableLiveData<Resource<LoginResp>>()
        mainRepository.getBanner(reqParam, {
            liveData.value = Resource.create(it)
        }, {
            liveData.value = Resource.create(it)
        }
        )
        return liveData
    }
}

