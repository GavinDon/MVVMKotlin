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
        fun <T> create(error: Throwable): ErrorSource<T> {
            //android.system.ErrnoException: connect failed: ENETUNREACH (Network is unreachable)
            return ErrorSource(error)
        }

        /**
         * 只处理code=0
         */
        fun <T> createa(data: BaseResponse<T>): Resource<T> {
            val code = data.code
            return if (code == 0) {
                val d = data.data ?: ""
                /*如果返回数据是一个集合判断集合是否为空*/
                return if (d is List<*> && d.size <= 0) {
                    EmptySource()
                } else {
                    SuccessSource(data)
                }
            } else {
                NotZeroSource(data.data, data.code, data.msg)

            }

        }

        fun <T> create(data: T): Resource<T> {

            return if (data is BaseResponse<*>) {
                if (data.code == 0) {
                    SuccessSource(data)
                } else {
                    NotZeroSource(data.data, data.code, data.msg)
                }
            } else {
                SuccessSource(data)
            }
        }


    }
}

//请求成功且size>=1
data class SuccessSource<T>(val body: T) : Resource<T>()

//请求成功数据size为0
class EmptySource<T> : Resource<T>()

//请求过程中发生的错误
data class ErrorSource<T>(val e: Throwable) : Resource<T>()

//反参不为约定的0为正常 其他的状态
data class NotZeroSource<T>(val body: T, val code: Int, val msg: String?) : Resource<T>()


