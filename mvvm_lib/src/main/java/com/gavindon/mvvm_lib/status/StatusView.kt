package com.gavindon.mvvm_lib.status

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * description:
 * Created by liNan on 2019/12/25 15:10

 */
class StatusView : ConstraintLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
    }
}

enum class Status {
    LOADING,
    NETWORK_ERROR,
    EMPTY
}