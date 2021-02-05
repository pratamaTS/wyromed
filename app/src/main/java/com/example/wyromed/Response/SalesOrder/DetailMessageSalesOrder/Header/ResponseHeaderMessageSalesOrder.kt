package com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header

import com.google.gson.annotations.SerializedName

open class ResponseHeaderMessageSalesOrder {
    @field:SerializedName("data")
    val data: DataHeaderMessageSalesOrder? = null

    @field:SerializedName("meta")
    val meta: MetaHeaderMessageSalesOrder? = null
}