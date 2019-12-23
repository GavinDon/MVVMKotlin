package com.gavindon.mvvm_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import com.gavindon.mvvm_kotlin.bean.LoginResp
import com.gavindon.mvvm_kotlin.repostory.MainRepostory
import com.gavindon.mvvm_lib.base.MVVMBaseViewModel
import com.gavindon.mvvm_lib.net.*

/**
 * description:
 * Created by liNan on  2019/12/17 14:28
 */
class MainViewModel : MVVMBaseViewModel() {

    private val mainRepostory: MainRepostory = MainRepostory()

    fun getLogin(reqParam: List<Pair<String, String>>): MutableLiveData<LoginResp> {
        val liveData = MutableLiveData<LoginResp>()
        mainRepostory.getBanner(reqParam)
        mainRepostory.getBanner2()
        return liveData
    }

}