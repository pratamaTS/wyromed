package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.ProvinceInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Province.ResponseProvince
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProvincePresenter(val provinceInterface: ProvinceInterface) {
    fun getProvince(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getProvince(map)
            .enqueue(object : Callback<ResponseProvince> {

                override fun onFailure(call: Call<ResponseProvince>, t: Throwable) {
                    provinceInterface.onErrorGetProvince(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseProvince>, response: Response<ResponseProvince>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        provinceInterface.onSuccessGetProvince(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        provinceInterface.onErrorGetProvince(message)
                    }
                }
            })
    }
}