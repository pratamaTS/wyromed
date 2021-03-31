package com.example.wyromed.Response.Warehouse

import com.example.wyromed.Data.Model.BaseMeta
import com.google.gson.annotations.SerializedName

open class ResponseWarehouse {

	@field:SerializedName("data")
	val data: ArrayList<DataWarehouse>? = null

	@field:SerializedName("meta")
	val meta: MetaWarehouse? = null
}
