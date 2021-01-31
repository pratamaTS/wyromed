package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.UserInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Login.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class UserPresenter(val userInterface: UserInterface) {
    fun getUser(context: Context){

        NetworkConfig.service(context)
            .getUser()
            .enqueue(object : Callback<ResponseLogin> {

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    try {
                        getUser(context)
                    } catch (e: SocketTimeoutException) {
                        userInterface.onErrorUser(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                    val body = response.body()

                    if (response.isSuccessful) {
                        userInterface.onSuccessUser(body?.data)
                    } else {
                        try {
                            getUser(context)
                        } catch (e: SocketTimeoutException) {
                            userInterface.onErrorUser(body?.meta?.message)
                        }
                    }
                }
            })
    }
}