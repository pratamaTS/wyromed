package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.GetSalesOrderInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.SalesOrder.GetAllSO.ResponseGetSalesOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GetSalesOrderPresenter(val getSalesOrderInterface: GetSalesOrderInterface) {
    fun getAllSO(context: Context){

        NetworkConfig.service(context)
            .getAllSO()
            .enqueue(object : Callback<ResponseGetSalesOrder> {

                override fun onFailure(call: Call<ResponseGetSalesOrder>, t: Throwable) {
                    getSalesOrderInterface.onErrorGetSalesOrder(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseGetSalesOrder>, response: Response<ResponseGetSalesOrder>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        getSalesOrderInterface.onSuccessGetSalesOrder(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = "error"
                        getSalesOrderInterface.onErrorGetSalesOrder(message)
                    }
                }
            })
    }
}