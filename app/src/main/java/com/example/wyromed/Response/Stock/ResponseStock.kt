package com.example.wyromed.Response.Stock

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponseStock {

	@field:SerializedName("data")
	val data: ArrayList<DataStock?> = ArrayList()

	@field:SerializedName("meta")
	val meta: MetaStock? = null
}