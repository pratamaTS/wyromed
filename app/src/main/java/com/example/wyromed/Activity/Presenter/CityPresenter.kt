package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.CityInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Province.ResponseCity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class CityPresenter(val cityInterface: CityInterface) {
    fun getCity(context: Context, stateID: String){
        //Declare Dynamic Headers
        val url: String = "cities/" + stateID

        NetworkConfig.service(context)
            .getCity(url)
            .enqueue(object : Callback<ResponseCity> {

                override fun onFailure(call: Call<ResponseCity>, t: Throwable) {
                    try {
                        getCity(context, stateID)
                    } catch (e: SocketTimeoutException) {
                        cityInterface.onErrorGetCity(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseCity>, response: Response<ResponseCity>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        cityInterface.onSuccessGetCity(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        try {
                            getCity(context, stateID)
                        } catch (e: SocketTimeoutException) {
                            cityInterface.onErrorGetCity(message)
                        }
                    }
                }
            })
    }
}