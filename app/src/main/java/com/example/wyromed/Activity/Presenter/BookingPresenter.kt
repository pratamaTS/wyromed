package com.example.wyromed.Activity.Presenter

import android.widget.Toast
import com.example.wyromed.Activity.Interface.BookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.BookingOrderDetails
import com.example.wyromed.Model.Body.BookingOrderHeader
import com.example.wyromed.Response.Booking.ResponseBooking
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap


class BookingPresenter(val bookingInterface: BookingInterface) {
    fun booking(
        tokenType: String?,
        token: String?,
        bookingHeaderBody: BookingOrderHeader,
        bookingDetailsBody: ArrayList<BookingOrderDetails>
    ){
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        val bodyMap = HashMap<String, Any>()

        // Header
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"
        map["Content-Length"] = "<calculated when request is sent>"
        map["Accept-Encoding"] = "gzip, deflate, br"

        // Body
        bodyMap.put("booking_order_header", bookingHeaderBody)
        bodyMap.put("booking_order_details", bookingDetailsBody)

        NetworkConfig.service()
            .storeBooking(map, bodyMap)
            .enqueue(object : Callback<ResponseBooking> {

                override fun onFailure(call: Call<ResponseBooking>, t: Throwable) {
                    bookingInterface.onErrorBooking(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseBooking>,
                    response: Response<ResponseBooking>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        bookingInterface.onSuccessBooking(message, data)
                    } else {
                        bookingInterface.onErrorBooking(error)
                    }
                }
            })
    }
}