package com.example.wyromed.Response.HeaderMessageBooking

import com.google.gson.annotations.SerializedName

open class ResponseHeaderMessageBooking {
    @field:SerializedName("data")
    val data: DataHeaderMessageBooking? = null

    @field:SerializedName("meta")
    val meta: MetaHeaderMessageBooking? = null
}