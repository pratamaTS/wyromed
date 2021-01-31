package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class PurchasedItemPresenter(val purchasedItemInterface: PurchasedItemInterface) {
    fun getAllPurchasedItem(context: Context){

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["type"] = "mobile"
        queryMap["entity"] = "BMHP"

        NetworkConfig.service(context)
            .getAllPurchasedItem(queryMap)
            .enqueue(object : Callback<ResponsePurchasedItem> {

                override fun onFailure(call: Call<ResponsePurchasedItem>, t: Throwable) {
                    try {
                        getAllPurchasedItem(context)
                    } catch (e: SocketTimeoutException) {
                        purchasedItemInterface.onErrorGetPurchasedItem(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponsePurchasedItem>, response: Response<ResponsePurchasedItem>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        purchasedItemInterface.onSuccessGetPurchasedItem(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        try {
                            getAllPurchasedItem(context)
                        } catch (e: SocketTimeoutException) {
                            purchasedItemInterface.onErrorGetPurchasedItem(message)
                        }
                    }
                }
            })
    }
}