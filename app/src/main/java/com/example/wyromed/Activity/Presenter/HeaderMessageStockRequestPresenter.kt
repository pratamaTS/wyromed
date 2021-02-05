package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.HeaderMessageStockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.ResponseHeaderMessageSalesOrder
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.ResponseHeaderMessageStockReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException


class HeaderMessageStockRequestPresenter(val headerMessageStockRequestInterface: HeaderMessageStockRequestInterface) {
    fun getHeaderMessageSR(
        context: Context,
        id: Int
    ){
        val url: String = "stockrequest/" + id

        NetworkConfig.service(context)
            .getHeaderMessageSR(url)
            .enqueue(object : Callback<ResponseHeaderMessageStockReq> {

                override fun onFailure(call: Call<ResponseHeaderMessageStockReq>, t: Throwable) {
                    try {
                        getHeaderMessageSR(context, id)
                    } catch (e: SocketTimeoutException) {
                        headerMessageStockRequestInterface.onErrorHeaderMessageSR(t.localizedMessage)
                    }
                }

                override fun onResponse(
                    call: Call<ResponseHeaderMessageStockReq>,
                    response: Response<ResponseHeaderMessageStockReq>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        headerMessageStockRequestInterface.onSuccessHeaderMessageSR(message, data)
                    } else {
                        try {
                            getHeaderMessageSR(context, id)
                        } catch (e: SocketTimeoutException) {
                            headerMessageStockRequestInterface.onErrorHeaderMessageSR(error)
                        }
                    }
                }
            })
    }
}