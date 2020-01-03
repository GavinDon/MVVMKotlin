package com.gavindon.mvvm_lib.net

/**
 * description:分为
 * Created by liNan on 2020/1/3 16:03

 */
class ExceptionHandle {
    companion object {
        private const val UNAUTHORIZED = 401
        private const val FORBIDDEN = 403
        private const val NOT_FOUND = 404
        private const val REQUEST_TIMEOUT = 408
        private const val INTERNAL_SERVER_ERROR = 500
        private const val SERVICE_UNAVAILABLE = 503
    }

}