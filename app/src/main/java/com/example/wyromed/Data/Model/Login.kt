package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

data class Login(

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null,

	@field:SerializedName("expires_at")
	val expiresAt: Int? = null,

	@field:SerializedName("token_type")
	val tokenType: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
