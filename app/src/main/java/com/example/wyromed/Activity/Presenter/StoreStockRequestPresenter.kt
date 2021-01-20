package com.example.wyromed.Activity.Presenter

import com.example.wyromed.Activity.Interface.StoreStockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.BookingOrderDetails
import com.example.wyromed.Model.Body.BookingOrderHeader
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.Body.StockRequestHeader
import com.example.wyromed.Response.Booking.ResponseBooking
import com.example.wyromed.Response.StockRequest.ResponseStockRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class StoreStockRequestPresenter(val storeStockRequestInterface: StoreStockRequestInterface) {
    fun storeStockRequest(tokenType: String?, token: String?, stockRequestHeader: StockRequestHeader, stockRequestDetails: ArrayList<StockRequestDetails?>){
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        val bodyMap = HashMap<String, Any>()

        // Header
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"
        map["Content-Length"] = "<calculated when request is sent>"
        map["Accept-Encoding"] = "gzip, deflate, br"

        // Body
        bodyMap.put("stock_request_header", stockRequestHeader)
        bodyMap.put("stock_request_details", stockRequestDetails)

        NetworkConfig.service()
            .storeStockRequest(map, bodyMap)
            .enqueue(object : Callback<ResponseStockRequest> {

                override fun onFailure(call: Call<ResponseStockRequest>, t: Throwable) {
                    storeStockRequestInterface.onErrorStockRequest(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseStockRequest>, response: Response<ResponseStockRequest>) {
                    val body = response.body()
                    val data = body?.data
                    val message = body?.meta?.message

                    if (response.isSuccessful) {
                        storeStockRequestInterface.onSuccessStockRequest(message, data)

                    } else {
                        storeStockRequestInterface.onErrorStockRequest(message)
                    }
                }
            })
    }
}