package com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail

import com.google.gson.annotations.SerializedName

open class ResponseDetailMessageStockReq {

    @field:SerializedName("data")
    val data: ArrayList<DataDetailMessageStockReq?> = ArrayList()

    @field:SerializedName("meta")
    val meta: MetaDetailMessageStockReq? = null
}