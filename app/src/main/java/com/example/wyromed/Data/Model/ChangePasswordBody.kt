package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

data class ChangePasswordBody (

    @field:SerializedName("old_password")
    val oldPassword: String? = null,

    @field:SerializedName("new_password")
    val newPassword: String? = null,

    @field:SerializedName("repassword")
    val rePassword: String? = null
)