package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.HistoryStockRequestInterface
import com.example.wyromed.Activity.Interface.StockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.StockRequest.ResponseGetStockRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import kotlin.collections.HashMap

class HistoryStockRequestPresenter(val historyStockRequestInterface: HistoryStockRequestInterface) {
    fun getAllHistoryStockRequest(context: Context){

        val map: MutableMap<String, String> = HashMap()

        NetworkConfig.service(context)
            .getAllStockRequest()
            .enqueue(object : Callback<ResponseGetStockRequest> {

                override fun onFailure(call: Call<ResponseGetStockRequest>, t: Throwable) {
                    try {
                        getAllHistoryStockRequest(context)
                    } catch (e: SocketTimeoutException) {
                        historyStockRequestInterface.onErrorGetHistoryStockRequest(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseGetStockRequest>, response: Response<ResponseGetStockRequest>) {
                    val body = response.body()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = response.errorBody().toString()

                    if (response.isSuccessful) {
                        historyStockRequestInterface.onSuccessGetHistoryStockRequest(message, data)

                    } else {
                        try {
                            getAllHistoryStockRequest(context)
                        } catch (e: SocketTimeoutException) {
                            historyStockRequestInterface.onErrorGetHistoryStockRequest(error)
                        }
                    }
                }
            })
    }
}