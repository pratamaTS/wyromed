package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.DetailMessageBookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.DetailMessageBooking.ResponseDetailMessageBooking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import java.util.*
import kotlin.collections.HashMap


class DetailMessageBookingPresenter(val detailMessageBookingInterface: DetailMessageBookingInterface) {
    fun getDetailMessageBooking(
        context: Context,
        id: Int
    ){
        val url: String = "bookingorder/details/" + id

        NetworkConfig.service(context)
            .getDetailMessageBooking(url)
            .enqueue(object : Callback<ResponseDetailMessageBooking> {

                override fun onFailure(call: Call<ResponseDetailMessageBooking>, t: Throwable) {
                   detailMessageBookingInterface.onErrorDetailMessageBooking(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseDetailMessageBooking>,
                    response: Response<ResponseDetailMessageBooking>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        detailMessageBookingInterface.onSuccessDetailMessageBooking(message, data)
                    } else {
                        detailMessageBookingInterface.onErrorDetailMessageBooking(error)
                    }
                }
            })
    }
}