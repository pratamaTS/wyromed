package com.example.wyromed.Base

interface BaseView<P> {

    var presenter: P

    fun init()
    fun showLoading(isLoading: Boolean)
    fun showLoadingContent(isLoading: Boolean)
    fun showEmptyPlaceHolder(isEmpty: Boolean)
    fun showErrorMessage(message: String?)
    fun showMessage(message: String?, duration: ToastDuration = ToastDuration.SHORT)
    fun pressBack()
    fun closeView()

    enum class ToastDuration {
        SHORT, LONG
    }

}