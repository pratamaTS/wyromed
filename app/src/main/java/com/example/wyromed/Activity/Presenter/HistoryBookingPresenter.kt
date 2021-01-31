package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.HistoryBookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Order.ResponseOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class HistoryBookingPresenter(val historyBookingInterface: HistoryBookingInterface) {
    fun getAllHistoryBooking(context: Context){

        val map: MutableMap<String, String> = HashMap()

        NetworkConfig.service(context)
            .getBookingOrdered()
            .enqueue(object : Callback<ResponseOrder> {

                override fun onFailure(call: Call<ResponseOrder>, t: Throwable) {
                    try {
                        getAllHistoryBooking(context)
                    } catch (e: SocketTimeoutException) {
                        historyBookingInterface.onErrorHistoryBooking(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseOrder>, response: Response<ResponseOrder>) {
                    val body = response.body()

                    val data = body?.data
                    val message = body?.meta?.message

                    if (response.isSuccessful) {
                        historyBookingInterface.onSuccessHistoryBooking(data)

                    } else {
                        try {
                            getAllHistoryBooking(context)
                        } catch (e: SocketTimeoutException) {
                            historyBookingInterface.onErrorHistoryBooking(message)
                        }
                    }
                }
            })
    }
}