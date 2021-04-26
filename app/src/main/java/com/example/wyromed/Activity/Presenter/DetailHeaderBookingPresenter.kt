package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.DetailMessageBookingInterface
import com.example.wyromed.Activity.Interface.HandoverDetailBookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.DetailMessageBooking.ResponseDetailMessageBooking
import com.example.wyromed.Response.Handover.ResponseHandoverDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.util.*
import kotlin.collections.HashMap


class DetailHeaderBookingPresenter(val handoverDetailBookingInterface: HandoverDetailBookingInterface) {
    fun getDetailHandoverBooking(
        context: Context,
        id: Int
    ){
       val url: String = "bookingorder/details/" + id

        NetworkConfig.service(context)
            .getDetailHandoverBooking(url)
            .enqueue(object : Callback<ResponseHandoverDetail> {

                override fun onFailure(call: Call<ResponseHandoverDetail>, t: Throwable) {
                    handoverDetailBookingInterface.onErrorHandoverDetailBooking(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseHandoverDetail>,
                    response: Response<ResponseHandoverDetail>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        handoverDetailBookingInterface.onSuccessHandoverDetailBooking(message, data)
                    } else {
                        handoverDetailBookingInterface.onErrorHandoverDetailBooking(error)
                    }
                }
            })
    }
}