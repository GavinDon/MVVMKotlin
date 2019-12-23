package com.gavindon.mvvm_kotlin.view

import android.os.Bundle
import androidx.lifecycle.Observer
import com.gavindon.mvvm_kotlin.base.BaseActivity
import com.gavindon.mvvm_kotlin.viewmodel.MainViewModel
import com.gavindon.mvvm_kotlin.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = getViewModel()
        val reqParam = listOf(
            Pair("loginName", "admin"),
            Pair("password", "123123")
        )
        mainViewModel.getLogin(reqParam).observe(this, Observer {
            tvMock.text = it.name
        })
    }

}
