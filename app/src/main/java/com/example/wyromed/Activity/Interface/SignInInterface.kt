package com.example.wyromed.Activity.Interface

interface SignInInterface {
    fun onSuccessLogin(tokenType: String?, token: String?, message: String?)
    fun onErrorLogin(msg:String?)
}