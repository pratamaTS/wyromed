package com.example.wyromed.Response.Booking

import com.google.gson.annotations.SerializedName

class MetaUpdateStatusBooking: ResponseUpdateStatusBooking() {

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("message")
    val message: String? = null

    @field:SerializedName("status")
    val status: Int? = null

    @field:SerializedName("error")
    val error: Int? = null
}