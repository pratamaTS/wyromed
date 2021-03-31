package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

data class EditProfileBody (

    @field:SerializedName("fullname")
    val fullname: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("avatar_path")
    val avatarPath: String? = null
)