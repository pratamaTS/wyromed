package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.RentalItemInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.RentalItem.ResponseRentalItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class RentalItemPresenter(val rentalItemInterface: RentalItemInterface) {
    fun getAllRentalItem(context: Context, start: String?, end: String?){

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["start"] = start.toString()
        queryMap["end"] = end.toString()

        NetworkConfig.service(context)
            .getAllRentalItem(queryMap)
            .enqueue(object : Callback<ResponseRentalItem> {

                override fun onFailure(call: Call<ResponseRentalItem>, t: Throwable) {
                    try {
                        getAllRentalItem(context, start, end)
                    } catch (e: SocketTimeoutException) {
                        rentalItemInterface.onErrorGetRentalItem(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseRentalItem>, response: Response<ResponseRentalItem>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        rentalItemInterface.onSuccessGetRentalItem(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        try {
                            getAllRentalItem(context, start, end)
                        } catch (e: SocketTimeoutException) {
                            rentalItemInterface.onErrorGetRentalItem(message)
                        }
                    }
                }
            })
    }
}