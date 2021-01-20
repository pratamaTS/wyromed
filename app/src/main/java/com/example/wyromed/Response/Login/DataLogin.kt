package com.example.wyromed.Response.Login

import com.google.gson.annotations.SerializedName

class DataLogin: ResponseLogin() {

	@field:SerializedName("sub")
	var sub: String? = null

	@field:SerializedName("role")
	var role: String? = null

	@field:SerializedName("role_id")
	var roleId: Int? = null

	@field:SerializedName("user_id")
	var userId: Int? = null

	@field:SerializedName("iss")
	var iss: String? = null

	@field:SerializedName("fullname")
	var fullname: String? = null

	@field:SerializedName("exp")
	var exp: Int? = null

	@field:SerializedName("iat")
	var iat: Int? = null

	@field:SerializedName("email")
	var email: String? = null

	@field:SerializedName("token_type")
	var token_type: String? = null

	@field:SerializedName("expires_at")
	var expires_at: String? = null

	@field:SerializedName("token")
	var token: String? = null
}
