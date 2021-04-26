package com.example.wyromed.Response.SalesOrder.GetAllSO

import com.google.gson.annotations.SerializedName

class MetaGetSalesOrder: ResponseGetSalesOrder() {

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: Int? = null
}