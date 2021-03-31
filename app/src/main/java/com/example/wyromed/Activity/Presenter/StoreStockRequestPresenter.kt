package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.StoreStockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.Body.StockRequestHeader
import com.example.wyromed.Response.StockRequest.ResponseStockRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class StoreStockRequestPresenter(val storeStockRequestInterface: StoreStockRequestInterface) {
    fun storeStockRequest(context: Context, stockRequestHeader: StockRequestHeader, stockRequestDetails: ArrayList<StockRequestDetails?>){

        val bodyMap = HashMap<String, Any>()

        // Body
        bodyMap.put("stock_request_header", stockRequestHeader)
        bodyMap.put("stock_request_details", stockRequestDetails)

        NetworkConfig.service(context)
            .storeStockRequest(bodyMap)
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