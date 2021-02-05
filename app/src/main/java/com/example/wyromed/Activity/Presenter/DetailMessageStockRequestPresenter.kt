package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.DetailMessageStockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.ResponseDetailMessageStockReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException


class DetailMessageStockRequestPresenter(val detailMessageStockRequestInterface: DetailMessageStockRequestInterface) {
    fun getDetailMessageSR(
        context: Context,
        id: Int
    ){
        val url: String = "stockrequest/details/" + id

        NetworkConfig.service(context)
            .getDetailMessageSR(url)
            .enqueue(object : Callback<ResponseDetailMessageStockReq> {

                override fun onFailure(call: Call<ResponseDetailMessageStockReq>, t: Throwable) {
                    try {
                        getDetailMessageSR(context, id)
                    } catch (e: SocketTimeoutException) {
                        detailMessageStockRequestInterface.onErrorDetailMessageSR(t.localizedMessage)
                    }
                }

                override fun onResponse(
                    call: Call<ResponseDetailMessageStockReq>,
                    response: Response<ResponseDetailMessageStockReq>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        detailMessageStockRequestInterface.onSuccessDetailMessageSR(message, data)
                    } else {
                        try {
                            getDetailMessageSR(context, id)
                        } catch (e: SocketTimeoutException) {
                            detailMessageStockRequestInterface.onErrorDetailMessageSR(error)
                        }
                    }
                }
            })
    }
}