package com.example.wyromed.Response.PurchasedItem

import com.google.gson.annotations.SerializedName

class MetaPurchasedItem: ResponsePurchasedItem() {
    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: Int? = null

    @field:SerializedName("message")
    val message: String? = null

    @field:SerializedName("error")
    val error: String? = ""
}