package com.gavindon.mvvm_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_kotlin.repository.MainRepository
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.utils.Parameters

/**
 * description:
 * Created by liNan on  2019/12/17 14:28
 */
class MainViewModel : MVVMBaseViewModel() {

    private val mainRepository: MainRepository = MainRepository()

    fun getLogin(reqParam:Parameters): MutableLiveData<LoginResp> {
        val liveData = MutableLiveData<LoginResp>()
        mainRepository.getBanner(reqParam){
            liveData.value = it
        }
        return liveData
    }}