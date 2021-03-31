package com.example.wyromed.Response.Warehouse

import com.google.gson.annotations.SerializedName

class MetaWarehouse: ResponseWarehouse() {

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("status")
    val status: Int? = null
}