package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.SignUpInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.RegisterBody
import com.example.wyromed.Model.User
import com.example.wyromed.Response.Login.DataLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpPresenter(val signUpInterface: SignUpInterface){
    fun signup(context: Context, registerBody: RegisterBody){

        NetworkConfig.service(context)
            .signup(registerBody)
            .enqueue(object : Callback<DataLogin> {

                override fun onFailure(call: Call<DataLogin>, t: Throwable){
                    signUpInterface.onErrorSignUp(t.message)
                }

                override fun onResponse(call: Call<DataLogin>, response : Response<DataLogin>){
                    val body = response.body()
                    val error = response.errorBody().toString()

                    if (response.isSuccessful){
                        signUpInterface.onSuccessSignUp(body?.meta?.message)
                    }
                    else {
                        signUpInterface.onErrorSignUp(body?.meta?.error)
                    }
                }
            })
    }
}