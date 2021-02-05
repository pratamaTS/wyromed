package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.TotalBookingOrderInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Booking.ResponseTotalBookingOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class TotalBookingPresenter(val totalBookingOrderInterface: TotalBookingOrderInterface) {
    fun getTotalBookingOrder(context: Context){
        var connection = false

        NetworkConfig.service(context)
            .getTotalBookingOrder()
            .enqueue(object : Callback<ResponseTotalBookingOrder> {

                override fun onFailure(call: Call<ResponseTotalBookingOrder>, t: Throwable) {
                    for (retries in 0..2){
                        getTotalBookingOrder(context)
                        if (connection == false){
                            continue
                        }
                        break
                    }

                    totalBookingOrderInterface.onErrorGetTotalBookingOrder(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseTotalBookingOrder>, response: Response<ResponseTotalBookingOrder>) {
                    connection = true

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