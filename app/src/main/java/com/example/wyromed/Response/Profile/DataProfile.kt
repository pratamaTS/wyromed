package com.example.wyromed.Response.Profile

import com.google.gson.annotations.SerializedName

class DataProfile: ResponseProfile() {

    @field:SerializedName("userid")
    val userID: Int? = null

    @field:SerializedName("fullname")
    val fullname: String? = null

    @field:SerializedName("email")
    val email: String? = null

    @field:SerializedName("phone")
    val phone: String? = null

    @field:SerializedName("profilepict")
    val profilepict: String? = null
}