package com.example.wyromed.Response.SalesOrder.GetAllSO

import com.google.gson.annotations.SerializedName

open class ResponseGetSalesOrder {

	@field:SerializedName("data")
	val data: ArrayList<DataGetSalesOrder?>? = null

	@field:SerializedName("meta")
	val meta: MetaGetSalesOrder? = null
}
