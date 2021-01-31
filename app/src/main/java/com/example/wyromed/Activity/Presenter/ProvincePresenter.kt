package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.ProvinceInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Province.ResponseProvince
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class ProvincePresenter(val provinceInterface: ProvinceInterface) {
    fun getProvince(context: Context){

        NetworkConfig.service(context)
            .getProvince()
            .enqueue(object : Callback<ResponseProvince> {

                override fun onFailure(call: Call<ResponseProvince>, t: Throwable) {
                    try {
                        getProvince(context)
                    } catch (e: SocketTimeoutException) {
                        provinceInterface.onErrorGetProvince(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseProvince>, response: Response<ResponseProvince>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        provinceInterface.onSuccessGetProvince(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        try {
                            getProvince(context)
                        } catch (e: SocketTimeoutException) {
                            provinceInterface.onErrorGetProvince(message)
                        }
                    }
                }
            })
    }
}