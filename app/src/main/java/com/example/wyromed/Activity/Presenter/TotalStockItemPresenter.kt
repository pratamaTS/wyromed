package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Activity.Interface.StockInterface
import com.example.wyromed.Activity.Interface.TotalStockInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import com.example.wyromed.Response.QuantityAvailableStock.ResponseTotalQtyStock
import com.example.wyromed.Response.Stock.ResponseStock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TotalStockItemPresenter(val totalStockItemInterface: TotalStockInterface) {
    fun getTotalStockItem(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["type"] = "mobile"

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getTotalStockItem(queryMap, map)
            .enqueue(object : Callback<ResponseTotalQtyStock> {

                override fun onFailure(call: Call<ResponseTotalQtyStock>, t: Throwable) {
                    totalStockItemInterface.onErrorGetTotalStockItem(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseTotalQtyStock>, response: Response<ResponseTotalQtyStock>) {
                    if (response.isSuccessful) {
                        val totalQty = response.body()?.data
                        totalStockItemInterface.onSuccessGetTotalStockItem(totalQty)

                        Log.d("Total Stock", totalQty.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        totalStockItemInterface.onErrorGetTotalStockItem(message)
                    }
                }
            })
    }
}