package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.SignInBody
import com.example.wyromed.Model.User
import com.example.wyromed.Response.Login.DataLogin
import com.example.wyromed.Activity.Interface.SignInInterface
import com.example.wyromed.Api.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInPresenter(val signInInterface: SignInInterface) {
    private lateinit var sessionManager: SessionManager

    fun signin(context: Context, signInBody:SignInBody){

        sessionManager = SessionManager(context)

        NetworkConfig.service(context)
            .signin(signInBody)
            .enqueue(object : Callback<DataLogin> {

                override fun onFailure(call: Call<DataLogin>, t: Throwable){
                    signInInterface.onErrorLogin(t.localizedMessage)
                }

                override fun onResponse(call: Call<DataLogin>, response : Response<DataLogin>){
                    val body = response.body()
                    val error = response.errorBody()
                    if (response.isSuccessful){
                        Log.d("token login", body?.data?.token.toString())
                        sessionManager.saveAuthToken(body?.data?.token.toString())
                        signInInterface.onSuccessLogin(body?.data?.token_type, body?.data?.token, body?.meta?.message)
                    }
                    else {
                        signInInterface.onErrorLogin(error.toString())
                    }
                }
            })
    }
}