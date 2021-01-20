package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Activity.Interface.StockInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import com.example.wyromed.Response.Stock.ResponseStock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StockItemPresenter(val stockItemInterface: StockInterface) {
    fun getAllStockItem(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["type"] = "mobile"
        queryMap["entity"] = ""

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getAllStockItem(queryMap, map)
            .enqueue(object : Callback<ResponseStock> {

                override fun onFailure(call: Call<ResponseStock>, t: Throwable) {
                    stockItemInterface.onErrorGetStockItem(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseStock>, response: Response<ResponseStock>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        stockItemInterface.onSuccessGetStockItem(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        stockItemInterface.onErrorGetStockItem(message)
                    }
                }
            })
    }
}