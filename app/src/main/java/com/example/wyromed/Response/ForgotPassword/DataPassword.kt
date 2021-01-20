package com.example.wyromed.Response.ForgotPassword

import com.google.gson.annotations.SerializedName

class DataPassword: ResponsePassword() {

    @field:SerializedName("user_id")
    val userID: Int? = null
}