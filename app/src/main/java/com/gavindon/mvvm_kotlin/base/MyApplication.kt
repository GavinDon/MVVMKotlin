package com.gavindon.mvvm_kotlin.base

import android.app.Application
import com.gavindon.mvvm_lib.net.HttpFrame
import com.gavindon.mvvm_lib.net.HttpManager

/**
 * description:
 * Created by liNan on  2019/12/19 15:25
 */
class MyApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        /**
         *初始化http
         */
        HttpManager.instance.apply {
            initHttp(HttpFrame.FUEL)
            baseUrl = "http://218.63.210.38:20026/app/"
        }.build()
    }
}