package com.example.wyromed.Response.Confirmation

import com.example.wyromed.Response.Login.DataLogin
import com.example.wyromed.Response.Login.MetaLogin
import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponseConfirmation {
	@field:SerializedName("data")
	val data: DataLogin? = null

	@field:SerializedName("meta")
	val meta: MetaLogin? = null
}
