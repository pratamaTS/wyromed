package com.example.wyromed.Base

interface BasePresenter {
    fun start()
    fun attachView(mView: Any?)
    fun detachView()
    fun cleanUp()
    fun onBackPressed()
}