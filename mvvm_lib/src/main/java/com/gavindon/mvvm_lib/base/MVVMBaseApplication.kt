package com.gavindon.mvvm_lib.base

import android.app.Application
import android.content.Context

/**
 * description:
 * Created by liNan on 2019/12/27 16:49

 */
open class MVVMBaseApplication : Application() {


    companion object {
        lateinit var appContext: Context
        val instance: MVVMBaseApplication by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { MVVMBaseApplication() }
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
    }


}