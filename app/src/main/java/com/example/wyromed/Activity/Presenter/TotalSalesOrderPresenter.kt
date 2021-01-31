package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.TotalSalesOrderInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.SalesOrder.ResponseTotalSalesOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class TotalSalesOrderPresenter(val totalSalesOrderInterface: TotalSalesOrderInterface) {
    fun getTotalSalesOrder(context: Context){

        NetworkConfig.service(context)
            .getTotalSalesOrder()
            .enqueue(object : Callback<ResponseTotalSalesOrder> {

                override fun onFailure(call: Call<ResponseTotalSalesOrder>, t: Throwable) {
                    try {
                        getTotalSalesOrder(context)
                    } catch (e: SocketTimeoutException) {
                        totalSalesOrderInterface.onErrorGetTotalSalesOrder(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseTotalSalesOrder>, response: Response<ResponseTotalSalesOrder>) {
                    if (response.isSuccessful) {
                        val totalQty = response.body()?.data
                        totalSalesOrderInterface.onSuccessGetTotalSalesOrder(totalQty)
                    } else {
                        val message = response.body()?.meta?.message
                        try {
                            getTotalSalesOrder(context)
                        } catch (e: SocketTimeoutException) {
                            totalSalesOrderInterface.onErrorGetTotalSalesOrder(message)
                        }
                    }
                }
            })
    }
}