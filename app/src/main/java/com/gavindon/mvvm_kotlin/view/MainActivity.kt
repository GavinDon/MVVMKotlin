package com.gavindon.mvvm_kotlin.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.gavindon.mvvm_kotlin.R
import com.gavindon.mvvm_kotlin.base.BaseActivity
import com.gavindon.mvvm_kotlin.viewmodel.MainViewModel
import com.gavindon.mvvm_lib.widgets.showToast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel

    override val layoutId: Int = R.layout.activity_main


    override fun onInit(savedInstanceState: Bundle?) {
        mainViewModel = getViewModel()
        reqUserInfo()
    }


    private fun reqUserInfo() {
        val reqParam = listOf(
            Pair("loginName", "admin"),
            Pair("password", "123123")
        )
        requestPermission(
            this,
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) {
            mainViewModel.getLogin(reqParam).observe(this, Observer {
                handlerResponseData(it, { loginResp ->
                    tvMock.text = loginResp?.education
                }, {
                    showToast("onRetry")
                })
            })
        }
    }

    override fun permissionForResult() {
        reqUserInfo()
    }


}
