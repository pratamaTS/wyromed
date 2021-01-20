package com.example.wyromed.Response.StockRequest

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponseGetStockRequest {

	@field:SerializedName("data")
	val data: ArrayList<DataGetStockRequest?> = ArrayList()

	@field:SerializedName("meta")
	val meta: MetaGetStockRequest? = null
}