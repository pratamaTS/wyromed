package com.example.wyromed.Response.DetailMessageBooking

import com.google.gson.annotations.SerializedName

open class ResponseDetailMessageBooking {

    @field:SerializedName("data")
    val data: ArrayList<DataDetailMessageBooking?> = ArrayList()

    @field:SerializedName("meta")
    val meta: MetaDetailMessageBooking? = null
}