package com.gavindon.mvvm_lib.net


/**
 * description:
 * Created by liNan on  2019/12/19 16:40
 */
sealed class Resource<in T> {

    companion object {
        /**
         * 分为两种error
         * http请求中发生的error
         * http请求完成发生的error
         */
        fun create(error: Throwable?) {
            if (error != null) {
                //android.system.ErrnoException: connect failed: ENETUNREACH (Network is unreachable)
            }
        }

        /**
         * 只处理code=0
         */
        fun <T> create(data: BaseResponse<T>?): Resource<T> {
            if (data == null){
                return  ErrorSource(data)
            }else{
                val code = data.code
                return if (code == 0) {
                    val d = data.data ?: ""
                    /*如果返回数据是一个集合判断集合是否为空*/
                    return if (d is List<*> && d.size <= 0) {
                        EmptySource()
                    } else {
                        SuccessSource(data.data)
                    }
                } else {
                    ErrorSource(data.data)
                }
            }

        }

    }

}

data class SuccessSource<T>(val body: T) : Resource<T>()
class EmptySource<T> : Resource<T>()
data class ErrorSource<T>(val e: T) : Resource<T>()


class ErrorManager {

    companion object {
        /**
         * 非0的code
         */
        fun CodeNotZero() {

        }

        /**
         *
         */
        fun httpError() {

        }
    }
}