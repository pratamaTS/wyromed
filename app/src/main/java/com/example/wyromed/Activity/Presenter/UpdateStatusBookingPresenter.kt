package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.CityInterface
import com.example.wyromed.Activity.Interface.UpdateStatusBookingInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Booking.ResponseUpdateStatusBooking
import com.example.wyromed.Response.Province.ResponseCity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateStatusBookingPresenter(val updateStatusBookingInterface: UpdateStatusBookingInterface) {
    fun updateStatusBooking(context: Context, id: String, status: String){
        //Declare Dynamic Headers
        val url: String = "bookingorder/" + id

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["status"] = status

        NetworkConfig.service(context)
            .updateBookingStatus(url, queryMap)
            .enqueue(object : Callback<ResponseUpdateStatusBooking> {

                override fun onFailure(call: Call<ResponseUpdateStatusBooking>, t: Throwable) {
                    updateStatusBookingInterface.onErrorUpdateStatusBooking(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseUpdateStatusBooking>, response: Response<ResponseUpdateStatusBooking>) {
                    if (response.isSuccessful) {
                        val message = response.body()?.meta?.message
                        updateStatusBookingInterface.onSuccessUpdateStatusBooking(message)
                    } else {
                        val message = response.body()?.meta?.message
                        updateStatusBookingInterface.onErrorUpdateStatusBooking(message)
                    }
                }
            })
    }
}