package com.example.wyromed.Response.Province

import com.google.gson.annotations.SerializedName

class DataCity: ResponseCity() {

    @field:SerializedName("city_id")
    val cityID: String? = null

    @field:SerializedName("name")
    val cityName: String? = null
}