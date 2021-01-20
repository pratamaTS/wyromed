package com.example.wyromed.Response.RentalItem

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponseRentalItem {

	@field:SerializedName("data")
	val data: ArrayList<DataRentalItem?> = ArrayList()

	@field:SerializedName("meta")
	val meta: MetaRentalItem? = null
}