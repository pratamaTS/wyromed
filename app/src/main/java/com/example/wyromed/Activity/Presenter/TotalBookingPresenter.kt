package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.TotalBookingOrderInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Booking.ResponseTotalBookingOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalBookingPresenter(val totalBookingOrderInterface: TotalBookingOrderInterface) {
    fun getTotalBookingOrder(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getTotalBookingOrder(map)
            .enqueue(object : Callback<ResponseTotalBookingOrder> {

                override fun onFailure(call: Call<ResponseTotalBookingOrder>, t: Throwable) {
                    totalBookingOrderInterface.onErrorGetTotalBookingOrder(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseTotalBookingOrder>, response: Response<ResponseTotalBookingOrder>) {
                    if (response.isSuccessful) {
                        val totalQty = response.body()?.data
                        totalBookingOrderInterface.onSuccessGetTotalBookingOrder(totalQty)
                    } else {
                        val message = response.body()?.meta?.message
                        totalBookingOrderInterface.onErrorGetTotalBookingOrder(message)
                    }
                }
            })
    }
}