package com.example.wyromed.Response.Confirmation

import com.google.gson.annotations.SerializedName

class DataConfirmation: ResponseConfirmation() {

    @field:SerializedName("user_id")
    val userID: String? = null
}