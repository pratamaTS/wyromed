package com.example.wyromed.Response.StockRequest

import com.google.gson.annotations.SerializedName

class MetaGetStockRequest: ResponseStockRequest() {
    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: Int? = null

    @field:SerializedName("message")
    val message: String? = null

    @field:SerializedName("error")
    val error: String? = ""
}