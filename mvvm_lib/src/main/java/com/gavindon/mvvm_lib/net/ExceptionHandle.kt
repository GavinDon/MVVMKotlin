package com.gavindon.mvvm_lib.net

import com.github.kittinunf.fuel.core.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException


/**
 * description:分为
 * Created by liNan on 2020/1/3 16:03

 */
object ExceptionHandle {

    private const val UNAUTHORIZED = 401
    private const val FORBIDDEN = 403
    private const val NOT_FOUND = 404
    private const val REQUEST_TIMEOUT = 408
    private const val INTERNAL_SERVER_ERROR = 500
    private const val SERVICE_UNAVAILABLE = 503
    fun handleException(t: Throwable): AppException {
        val ex: AppException
        when (t.cause) {
            is HttpException -> {
                ex = AppException(ERROR.NETWORK_ERROR)
            }
            is SocketTimeoutException -> {
                //服务器响应的超时
                ex = AppException(ERROR.TIMEOUT_ERROR)
            }
            is ConnectException -> {
                ex = AppException(ERROR.NETWORK_ERROR)
            }
            is UnknownHostException -> {
                ex = AppException(ERROR.TIMEOUT_ERROR)

            }
            is SSLException -> {
                ex = AppException(ERROR.SSL_ERROR)
            }
            else -> {
                ex = AppException(ERROR.UNKNOWN)
            }
        }
        return ex

    }


}

class AppException : Exception {
    var errorMsg: String
    var errCode: Int = 0

    constructor(errCodeInput: Int, error: String?) : super(error) {
        errorMsg = error ?: "请求失败，请稍后再试"
        errCode = errCodeInput
    }

    constructor(error: ERROR) {
        errCode = error.getKey()
        errorMsg = error.getValue()
    }
}

enum class ERROR(private val code: Int, private val err: String) {

    /**
     * 未知错误
     */
    UNKNOWN(1000, "未知错误"),
    /**
     * 解析错误
     */
    PARSE_ERROR(1001, "解析错误"),
    /**
     * 网络错误
     */
    NETWORK_ERROR(1002, "网络错误"),

    /**
     * 协议出错
     */
    HTTP_ERROR(1003, "协议出错"),

    /**
     * 证书出错
     */
    SSL_ERROR(1004, "证书出错"),

    /**
     * 连接超时
     */
    TIMEOUT_ERROR(1006, "连接超时");

    fun getValue(): String {
        return err
    }

    fun getKey(): Int {
        return code
    }

}