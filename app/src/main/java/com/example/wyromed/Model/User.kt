package com.example.wyromed.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
class User {

	@field:SerializedName("role")
	val role: String? = null

	@field:SerializedName("role_id")
	val roleId: Int? = null

	@field:SerializedName("user_id")
	val userId: Int? = null

	@field:SerializedName("email")
	var email: String? = ""

	@field:SerializedName("password")
	var password: String? = null

	@field:SerializedName("repassword")
	var repassword: String? = null

	@field:SerializedName("fullname")
	var fullname: String? = null

	@field:SerializedName("phone")
	var phone: String? = null

	@field:SerializedName("token")
	var token: String? = null

}

