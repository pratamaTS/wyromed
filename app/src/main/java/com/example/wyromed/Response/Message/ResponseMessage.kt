package com.example.wyromed.Response.Message

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponseMessage {

	@field:SerializedName("data")
	val data: DataMessage? = null

	@field:SerializedName("meta")
	val meta: MetaMessage? = null
}