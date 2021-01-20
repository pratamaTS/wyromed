package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.UserInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Login.ResponseLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserPresenter(val userInterface: UserInterface) {
    fun getUser(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
            map["Authorization"] = tokenHeader
            map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getUser(map)
            .enqueue(object : Callback<ResponseLogin> {

                override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                    userInterface.onErrorUser(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                    val body = response.body()

                    if (response.isSuccessful) {
                        userInterface.onSuccessUser(body?.data)

                        Log.d("Data Body", body?.data.toString())
                    } else {
                        userInterface.onErrorUser(body?.meta?.message)
                    }
                }
            })
    }
}