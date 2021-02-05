package com.example.wyromed.Activity.Presenter

import android.content.Context
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
import java.net.SocketTimeoutException

class TotalStockItemPresenter(val totalStockItemInterface: TotalStockInterface) {
    fun getTotalStockItem(context: Context){
        var connection = false
        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["type"] = "mobile"

        NetworkConfig.service(context)
            .getTotalStockItem(queryMap)
            .enqueue(object : Callback<ResponseTotalQtyStock> {

                override fun onFailure(call: Call<ResponseTotalQtyStock>, t: Throwable) {
                    for (retries in 0..2){
                        getTotalStockItem(context)
                        if (connection == false){
                            continue
                        }
                        break
                    }

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