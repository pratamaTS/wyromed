package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

class BaseMeta: ResponseError() {

    @SerializedName("ok")
    val ok: Boolean? = false

    @SerializedName("status")
    val status: String? = null

    @field:SerializedName("error")
    val error: String? = null

    @field:SerializedName("message")
    val message: String? = null
}