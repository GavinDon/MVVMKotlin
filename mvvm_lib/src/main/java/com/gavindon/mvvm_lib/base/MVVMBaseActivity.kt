package com.gavindon.mvvm_lib.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.gavindon.mvvm_lib.R
import com.gavindon.mvvm_lib.base.PermissionCode.cameraCode
import com.gavindon.mvvm_lib.base.my_interface.IView
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission


/**
 * description:
 * Created by liNan on 2019/12/27 11:22

 */
abstract class MVVMBaseActivity : AppCompatActivity(), IView {


    override fun initStateView() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        onInit(savedInstanceState)
        initStateView()
    }

    @get:LayoutRes
    abstract val layoutId: Int

    protected abstract fun onInit(savedInstanceState: Bundle?)


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        permissionForResult()
    }

    fun requestPermission(
        context: Activity,
        vararg permission: String,
        onGrantedAction: () -> Unit
    ) {
        AndPermission.with(context)
            .runtime()
            .permission(permission)
            .onGranted {
                if (it.size == permission.size) {
                    onGrantedAction()
                }
            }
            .onDenied {
                val permissionNames: List<String?> =
                    Permission.transformText(context, it)
                val message = context.getString(
                    R.string.message_permission_always_failed,
                    TextUtils.join("\n", permissionNames)
                )
                //当点击禁止且勾选不再询问时
                if (AndPermission.hasAlwaysDeniedPermission(context, it)) {
                    showDeniedPermission(message)
                } else {
                    //当点击禁止时
                    showDeniedPermission(message)

                }
            }.start()
    }

    /**
     * 提示缺少什么权限
     */
    private fun showDeniedPermission(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("去设置") { _, _ ->
                AndPermission.with(this).runtime().setting()
                    .start(cameraCode)
            }.setNegativeButton("取消") { _, _ ->
            }.show()
    }

}

object PermissionCode {
    const val cameraCode = 0x1001
}