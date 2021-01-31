package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Activity.Interface.StockInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import com.example.wyromed.Response.Stock.ResponseStock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class StockItemPresenter(val stockItemInterface: StockInterface) {
    fun getAllStockItem(context: Context){

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["type"] = "mobile"
        queryMap["entity"] = ""

        NetworkConfig.service(context)
            .getAllStockItem(queryMap)
            .enqueue(object : Callback<ResponseStock> {

                override fun onFailure(call: Call<ResponseStock>, t: Throwable) {
                    try {
                        getAllStockItem(context)
                    } catch (e: SocketTimeoutException) {
                        stockItemInterface.onErrorGetStockItem(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseStock>, response: Response<ResponseStock>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        stockItemInterface.onSuccessGetStockItem(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.errorBody().toString()
                        try {
                            getAllStockItem(context)
                        } catch (e: SocketTimeoutException) {
                            stockItemInterface.onErrorGetStockItem(message)
                        }
                    }
                }
            })
    }
}