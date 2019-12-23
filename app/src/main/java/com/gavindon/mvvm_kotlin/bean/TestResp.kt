package com.gavindon.mvvm_kotlin.bean

/**
 * description:
 * Created by liNan on  2019/12/19 14:09
 */

data class LoginResp(
    val name: String,
    val education: String,
    val email: String,
    val icon: String,
    val id: Int,
    val modelName: String,
    val nationality: String,
    val phone: String,
    val pwd: String,
    val sex: String,
    //1签到 ,0未签到
    val signStatus: String,
    val trueName: String,
    val mesTag: String
)