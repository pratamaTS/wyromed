package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Login.DataLogin

interface UserInterface {
    fun onSuccessUser(dataLogin: DataLogin?)
    fun onErrorUser(msg:String?)
}