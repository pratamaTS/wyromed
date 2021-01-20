package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.BookingInterface
import com.example.wyromed.Activity.Interface.OrderedInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.BookingOrderDetails
import com.example.wyromed.Model.Body.BookingOrderHeader
import com.example.wyromed.Response.Booking.ResponseBooking
import com.example.wyromed.Response.Order.ResponseOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OrderedPresenter(val orderedInterface: OrderedInterface) {
    fun getAllBookingOrdered(tokenType: String?, token: String?){
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getBookingOrdered(map)
            .enqueue(object : Callback<ResponseOrder> {

                override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                    orderedInterface.onErrorOrdered(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                    val body = response.body()

                    val data = body?.data
                    val message = body?.meta?.message

                    if (response.isSuccessful) {
                        orderedInterface.onSuccessOrdered(data)

                    } else {
                        orderedInterface.onErrorOrdered(message)
                    }
                }
            })
    }
}