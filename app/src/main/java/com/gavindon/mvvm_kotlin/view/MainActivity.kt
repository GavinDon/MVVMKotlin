package com.gavindon.mvvm_kotlin.view

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.gavindon.mvvm_kotlin.R
import com.gavindon.mvvm_kotlin.base.BaseActivity
import com.gavindon.mvvm_kotlin.viewmodel.MainViewModel
import com.gavindon.mvvm_lib.widgets.TipDialog
import com.gavindon.mvvm_lib.widgets.showToast
import com.yanzhenjie.permission.runtime.Permission
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override val layoutId: Int = R.layout.activity_main

    override fun onInit(savedInstanceState: Bundle?) {
        mainViewModel = getViewModel(this)
        reqUserInfo()
        TipDialog().builds {
            title = "title"
            message = "mesage"
            confirm {

            }
        }.show(supportFragmentManager, "")
        tvMock.setOnClickListener {
            showToast("hel")
        }
    }


    private fun reqUserInfo() {
        val reqParam = listOf(
            Pair("loginName", "admin"),
            Pair("password", "123123")
        )
        requestPermission(this, Permission.CAMERA, Permission.WRITE_EXTERNAL_STORAGE) {
            mainViewModel.getLogin(reqParam).observe(this, Observer {
                handlerResponseData(it, { loginResp ->
                    tvMock.text = loginResp.name
                }, {
                    Log.i("expo", "need retry")
                    this.reqUserInfo()
                })
            })
        }
    }

    override fun permissionForResult() {
        reqUserInfo()
    }


}
