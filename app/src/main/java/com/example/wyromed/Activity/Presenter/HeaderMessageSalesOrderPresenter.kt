package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.HeaderMessageSalesOrderInterface
import com.example.wyromed.Activity.Interface.HeaderMessageStockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.ResponseHeaderMessageSalesOrder
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.ResponseHeaderMessageStockReq
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HeaderMessageSalesOrderPresenter(val headerMessageSalesOrderInterface: HeaderMessageSalesOrderInterface) {
    fun getHeaderMessageSO(
        context: Context,
        id: Int
    ){
        val url: String = "stockrequest/" + id

        NetworkConfig.service(context)
            .getHeaderMessageSO(url)
            .enqueue(object : Callback<ResponseHeaderMessageSalesOrder> {

                override fun onFailure(call: Call<ResponseHeaderMessageSalesOrder>, t: Throwable) {
                    headerMessageSalesOrderInterface.onErrorHeaderMessageSO(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseHeaderMessageSalesOrder>,
                    response: Response<ResponseHeaderMessageSalesOrder>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        headerMessageSalesOrderInterface.onSuccessHeaderMessageSO(message, data)
                    } else {
                        headerMessageSalesOrderInterface.onErrorHeaderMessageSO(error)
                    }
                }
            })
    }
}