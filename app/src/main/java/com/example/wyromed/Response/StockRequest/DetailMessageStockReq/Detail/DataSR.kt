package com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail

import com.google.gson.annotations.SerializedName

data class DataSR(

	@field:SerializedName("qty_after")
	val qtyAfter: Int? = null,

	@field:SerializedName("qty_transferred")
	val qtyTransferred: Int? = null,

	@field:SerializedName("product_id")
	val productId: Int? = null,

	@field:SerializedName("product_name")
	val productName: String? = null,

	@field:SerializedName("qty_before")
	val qtyBefore: Int? = null,

	@field:SerializedName("qty_requested")
	val qtyRequested: Int? = null
)
