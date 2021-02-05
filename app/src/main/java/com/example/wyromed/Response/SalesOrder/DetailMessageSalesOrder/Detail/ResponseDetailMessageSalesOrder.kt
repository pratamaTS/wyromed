package com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail

import com.google.gson.annotations.SerializedName

open class ResponseDetailMessageSalesOrder {

    @field:SerializedName("data")
    val data: ArrayList<DataDetailMessageSalesOrder?> = ArrayList()

    @field:SerializedName("meta")
    val meta: MetaDetailMessageSalesOrder? = null
}