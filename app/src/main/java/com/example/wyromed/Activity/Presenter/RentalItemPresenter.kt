package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.RentalItemInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.RentalItem.ResponseRentalItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RentalItemPresenter(val rentalItemInterface: RentalItemInterface) {
    fun getAllRentalItem(tokenType: String?, token: String?, start: String?, end: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["start"] = start.toString()
        queryMap["end"] = end.toString()

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getAllRentalItem(queryMap, map)
            .enqueue(object : Callback<ResponseRentalItem> {

                override fun onFailure(call: Call<ResponseRentalItem>, t: Throwable) {
                    rentalItemInterface.onErrorGetRentalItem(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseRentalItem>, response: Response<ResponseRentalItem>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        rentalItemInterface.onSuccessGetRentalItem(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        rentalItemInterface.onErrorGetRentalItem(message)
                    }
                }
            })
    }
}