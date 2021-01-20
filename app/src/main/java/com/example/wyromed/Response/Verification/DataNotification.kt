package com.example.wyromed.Response.Verification

import com.google.gson.annotations.SerializedName

class DataNotification: ResponseNotification() {

    @field:SerializedName("token")
    val token: Int? = null
}