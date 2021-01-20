package com.example.wyromed.Response.Province

import com.google.gson.annotations.SerializedName

class DataProvince: ResponseProvince() {

    @field:SerializedName("state_id")
    val stateID: String? = null

    @field:SerializedName("name")
    val stateName: String? = null
}