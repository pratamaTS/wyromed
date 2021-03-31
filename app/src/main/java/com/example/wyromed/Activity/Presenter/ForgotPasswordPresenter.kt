package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.ForgotPasswordInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.SignInBody
import com.example.wyromed.Response.Login.DataLogin
import com.example.wyromed.Activity.Interface.SignInInterface
import com.example.wyromed.Api.ErrorUtils.parseError
import com.example.wyromed.Data.Connection.Run
import com.example.wyromed.Data.Connection.SessionManager
import com.example.wyromed.Data.Model.BaseMeta
import com.example.wyromed.Data.Model.ResponseError
import com.example.wyromed.Model.Body.ForgotPasswordBody
import com.example.wyromed.Response.ForgotPassword.MetaPassword
import com.example.wyromed.Response.Warehouse.DataWarehouse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordPresenter(val forgotPasswordInterface: ForgotPasswordInterface) {
    private lateinit var sessionManager: SessionManager

    fun resetPassword(context: Context, forgotPasswordBody: ForgotPasswordBody){

        Run.after(1000, {
            NetworkConfig.service(context)
                .forgotPassword(forgotPasswordBody)
                .enqueue(object : Callback<MetaPassword> {

                    override fun onFailure(call: Call<MetaPassword>, t: Throwable){
                        forgotPasswordInterface.onErrorForgotPassword(t.localizedMessage)
                    }

                    override fun onResponse(call: Call<MetaPassword>, response : Response<MetaPassword>){
                        val body = response.body()
                        if (response.isSuccessful){
                            forgotPasswordInterface.onSuccessForgotPassword(body?.meta?.message)
                        }
                        else {
                            val error: ResponseError? = parseError(response, context)

                            forgotPasswordInterface.onErrorForgotPassword(error?.meta?.error.toString())
                        }
                    }
                })
        })
    }
}