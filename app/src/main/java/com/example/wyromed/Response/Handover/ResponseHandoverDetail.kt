package com.example.wyromed.Response.Handover

import com.google.gson.annotations.SerializedName

open class ResponseHandoverDetail {

    @field:SerializedName("data")
    val data: ArrayList<DataHandoverDetail?> = ArrayList()

    @field:SerializedName("meta")
    val meta: MetaHandoverDetail? = null
}