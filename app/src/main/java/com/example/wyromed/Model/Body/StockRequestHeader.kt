package com.example.wyromed.Model.Body

import com.google.gson.annotations.SerializedName

data class StockRequestHeader(

	@field:SerializedName("note")
	var note: String? = null,

	@field:SerializedName("warehouse_pusat_id")
	var warehousePusatId: Int? = null,

	@field:SerializedName("type")
	var type: String? = null
)
