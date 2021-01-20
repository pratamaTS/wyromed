package com.example.wyromed.Response.PurchasedItem

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponsePurchasedItem {

	@field:SerializedName("data")
	val data: ArrayList<DataPurchasedItem?> = ArrayList()

	@field:SerializedName("meta")
	val meta: MetaPurchasedItem? = null
}