package com.example.wyromed.Activity.Presenter

import com.example.wyromed.Activity.Interface.HistoryBookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Order.ResponseOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryBookingPresenter(val historyBookingInterface: HistoryBookingInterface) {
    fun getAllHistoryBooking(tokenType: String?, token: String?){
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getBookingOrdered(map)
            .enqueue(object : Callback<ResponseOrder> {

                override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                    historyBookingInterface.onErrorHistoryBooking(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                    val body = response.body()

                    val data = body?.data
                    val message = body?.meta?.message

                    if (response.isSuccessful) {
                        historyBookingInterface.onSuccessHistoryBooking(data)

                    } else {
                        historyBookingInterface.onErrorHistoryBooking(message)
                    }
                }
            })
    }
}