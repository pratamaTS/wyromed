package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.HandoverHeaderBookingInterface
import com.example.wyromed.Activity.Interface.HeaderMessageBookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Handover.ResponseHandoverHeader
import com.example.wyromed.Response.HeaderMessageBooking.ResponseHeaderMessageBooking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap


class HeaderHandoverBookingPresenter(val headerHandoverHeaderBookingInterface: HandoverHeaderBookingInterface) {
    fun getHeaderHandOverBooking(
        context: Context,
        id: Int
    ){

        val url: String = "bookingorder/" + id

        NetworkConfig.service(context)
            .getHeaderHandoverBooking(url)
            .enqueue(object : Callback<ResponseHandoverHeader> {

                override fun onFailure(call: Call<ResponseHandoverHeader>, t: Throwable) {
                    headerHandoverHeaderBookingInterface.onErrorHandoverHeaderBooking(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseHandoverHeader>,
                    response: Response<ResponseHandoverHeader>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        headerHandoverHeaderBookingInterface.onSuccessHandoverHeaderBooking(message, data)
                    } else {
                        headerHandoverHeaderBookingInterface.onErrorHandoverHeaderBooking(error)
                    }
                }
            })
    }
}