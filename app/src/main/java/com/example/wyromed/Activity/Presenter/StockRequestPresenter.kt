package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.StockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.StockRequest.ResponseGetStockRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import kotlin.collections.HashMap

class StockRequestPresenter(val stockRequestInterface: StockRequestInterface) {
    fun getAllStockRequest(context: Context){

        NetworkConfig.service(context)
            .getAllStockRequest()
            .enqueue(object : Callback<ResponseGetStockRequest> {

                override fun onFailure(call: Call<ResponseGetStockRequest>, t: Throwable) {
                    try {
                        getAllStockRequest(context)
                    } catch (e: SocketTimeoutException) {
                        stockRequestInterface.onErrorGetStockRequest(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseGetStockRequest>, response: Response<ResponseGetStockRequest>) {
                    val body = response.body()
                    val data = body?.data
                    val message = body?.meta?.message

                    if (response.isSuccessful) {
                        stockRequestInterface.onSuccessGetStockRequest(message, data)

                    } else {
                        try {
                            getAllStockRequest(context)
                        } catch (e: SocketTimeoutException) {
                            stockRequestInterface.onErrorGetStockRequest(message)
                        }
                    }
                }
            })
    }
}