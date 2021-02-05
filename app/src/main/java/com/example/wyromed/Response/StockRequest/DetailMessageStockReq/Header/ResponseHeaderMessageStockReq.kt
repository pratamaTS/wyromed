package com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header

import com.google.gson.annotations.SerializedName

open class ResponseHeaderMessageStockReq {
    @field:SerializedName("data")
    val data: DataHeaderMessageStockReq? = null

    @field:SerializedName("meta")
    val meta: MetaHeaderMessageStockReq? = null
}