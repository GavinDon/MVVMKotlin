package com.gavindon.mvvm_lib.base

import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


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
        /*LOGGER初始化*/
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)
            .tag("wyh-logger") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }


}