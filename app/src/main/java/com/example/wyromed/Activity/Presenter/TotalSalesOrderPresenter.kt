package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.TotalSalesOrderInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.SalesOrder.ResponseTotalSalesOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalSalesOrderPresenter(val totalSalesOrderInterface: TotalSalesOrderInterface) {
    fun getTotalSalesOrder(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"

        NetworkConfig.service()
            .getTotalSalesOrder(map)
            .enqueue(object : Callback<ResponseTotalSalesOrder> {

                override fun onFailure(call: Call<ResponseTotalSalesOrder>, t: Throwable) {
                    totalSalesOrderInterface.onErrorGetTotalSalesOrder(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseTotalSalesOrder>, response: Response<ResponseTotalSalesOrder>) {
                    if (response.isSuccessful) {
                        val totalQty = response.body()?.data
                        totalSalesOrderInterface.onSuccessGetTotalSalesOrder(totalQty)
                    } else {
                        val message = response.body()?.meta?.message
                        totalSalesOrderInterface.onErrorGetTotalSalesOrder(message)
                    }
                }
            })
    }
}