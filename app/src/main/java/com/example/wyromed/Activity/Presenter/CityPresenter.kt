package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.CityInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Province.ResponseCity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityPresenter(val cityInterface: CityInterface) {
    fun getCity(tokenType: String?, token: String?, stateID: String){
        //Declare Dynamic Headers
        val url: String = "cities/" + stateID
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getCity(url, map)
            .enqueue(object : Callback<ResponseCity> {

                override fun onFailure(call: Call<ResponseCity>, t: Throwable) {
                    cityInterface.onErrorGetCity(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseCity>, response: Response<ResponseCity>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        cityInterface.onSuccessGetCity(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        cityInterface.onErrorGetCity(message)
                    }
                }
            })
    }
}