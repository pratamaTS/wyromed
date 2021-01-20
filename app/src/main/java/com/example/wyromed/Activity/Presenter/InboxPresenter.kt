package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.InboxInterface
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Activity.Interface.StockInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Inbox.ResponseInbox
import com.example.wyromed.Response.PurchasedItem.ResponsePurchasedItem
import com.example.wyromed.Response.Stock.ResponseStock
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InboxPresenter(val inboxInterface: InboxInterface) {
    fun getAllInbox(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()

        // Query Param Map
//        val queryMap: MutableMap<String, String> = HashMap()
//        queryMap["search"] = ""

        // Header Map
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getAllInbox(map)
            .enqueue(object : Callback<ResponseInbox> {

                override fun onFailure(call: Call<ResponseInbox>, t: Throwable) {
                    inboxInterface.onErrorGetInbox(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseInbox>, response: Response<ResponseInbox>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        inboxInterface.onSuccessGetInbox(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        inboxInterface.onErrorGetInbox(message)
                    }
                }
            })
    }
}