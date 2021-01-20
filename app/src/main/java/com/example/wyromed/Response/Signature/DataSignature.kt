package com.example.wyromed.Response.Signature

import com.google.gson.annotations.SerializedName

class DataSignature: ResponseSignature() {

    @field:SerializedName("user_id")
    val userID: Int? = null
}