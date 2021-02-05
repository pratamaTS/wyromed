package com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail

import com.google.gson.annotations.SerializedName

class MetaDetailMessageStockReq: ResponseDetailMessageSalesOrder() {

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: Int? = null

    @field:SerializedName("message")
    val message: String? = null

    @field:SerializedName("error")
    val error: String? = ""
}