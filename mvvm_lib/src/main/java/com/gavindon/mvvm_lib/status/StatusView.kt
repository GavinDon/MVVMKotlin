package com.gavindon.mvvm_lib.status

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.gavindon.mvvm_lib.R


/**
 * description:
 * Created by liNan on 2019/12/25 15:10

 */
class StatusView : ConstraintLayout {

    @LayoutRes
    private var emptyView: Int = R.layout.custom_empty_view
    @LayoutRes
    private var netWorkErrorView: Int = R.layout.custom_no_network_view
    @LayoutRes
    private var retryView = R.layout.custom_retry_view
    private val defaultLayoutParams: LayoutParams =
        LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.StatusView)
        val N: Int = typedArray.indexCount
        for (i in 0 until N) {
            when (val attr: Int = typedArray.getIndex(i)) {
                R.styleable.StatusView_empty_view -> {
                    emptyView = typedArray.getResourceId(attr, R.layout.toast)
                }
                R.styleable.StatusView_network_view -> {
                    netWorkErrorView = typedArray.getResourceId(attr, R.layout.toast)
                }

            }
        }
        typedArray.recycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun showEmpty() {
        val vue = inflate(context, emptyView, null)
        this.addView(vue, defaultLayoutParams)
    }

    fun showNoNetWork() {
        val vue = inflate(context, netWorkErrorView, null)
        this.addView(vue, defaultLayoutParams)
    }

    fun showRetryView(onRetry: () -> Unit) {
        val vue = inflate(context, retryView, null)
        val flag = vue.findViewById<TextView>(R.id.retryViewFlag)
        this.addView(vue, defaultLayoutParams)
        flag.setOnClickListener {
            onRetry()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()

    }

}

enum class Status {
    LOADING,
    NETWORK_ERROR,
    EMPTY
}