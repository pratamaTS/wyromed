package com.example.wyromed.Activity.Presenter

import android.content.Context
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
import java.net.SocketTimeoutException

class OrderedPresenter(val orderedInterface: OrderedInterface) {
    fun getAllBookingOrdered(context: Context){

        val map: MutableMap<String, String> = HashMap()

        NetworkConfig.service(context)
            .getBookingOrdered()
            .enqueue(object : Callback<ResponseOrder> {

                override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                    try {
                        getAllBookingOrdered(context)
                    } catch (e: SocketTimeoutException) {
                        orderedInterface.onErrorOrdered(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                    val body = response.body()
                    val error = response.errorBody().toString()
                    val data = body?.data
                    val message = body?.meta?.message

                    if (response.isSuccessful) {
                        orderedInterface.onSuccessOrdered(data)

                    } else {
                        try {
                            getAllBookingOrdered(context)
                        } catch (e: SocketTimeoutException) {
                            orderedInterface.onErrorOrdered(error)
                        }
                    }
                }
            })
    }
}