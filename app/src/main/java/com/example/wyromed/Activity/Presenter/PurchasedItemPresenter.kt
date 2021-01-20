package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PurchasedItemPresenter(val purchasedItemInterface: PurchasedItemInterface) {
    fun getAllPurchasedItem(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["type"] = "mobile"
        queryMap["entity"] = "BMHP"

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getAllPurchasedItem(queryMap, map)
            .enqueue(object : Callback<ResponsePurchasedItem> {

                override fun onFailure(call: Call<ResponsePurchasedItem>, t: Throwable) {
                    purchasedItemInterface.onErrorGetPurchasedItem(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponsePurchasedItem>, response: Response<ResponsePurchasedItem>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        purchasedItemInterface.onSuccessGetPurchasedItem(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        purchasedItemInterface.onErrorGetPurchasedItem(message)
                    }
                }
            })
    }
}