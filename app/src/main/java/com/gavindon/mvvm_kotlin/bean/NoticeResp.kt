package com.gavindon.mvvm_kotlin.bean

import java.io.Serializable


/**
 * description:通知公告
 * Created by liNan on  2018/12/8 20:05
 */

data class NoticeResp(
    val content: String,
    val create_by: Int,
    val departmentName: String,
    val department_id: Int,
    val document_id: String,
    val gmt_create: String,
    val gmt_modified: String,
    val id: Int,
    val name: String,
    val public_time: Any,
    val remark: Any,
    val state: Int,
    val title: String,
    val update_by: Any,
    val userName: String
) : Serializable