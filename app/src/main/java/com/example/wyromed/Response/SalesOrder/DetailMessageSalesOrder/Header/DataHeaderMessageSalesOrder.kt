package com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header

import com.google.gson.annotations.SerializedName

class DataHeaderMessageSalesOrder: ResponseHeaderMessageSalesOrder() {

	@field:SerializedName("transaction_date")
	val transactionDate: String? = null

	@field:SerializedName("note")
	val note: String? = null

	@field:SerializedName("year")
	val year: Int? = null

	@field:SerializedName("weekday")
	val weekday: String? = null

	@field:SerializedName("stock_request_id")
	val stockRequestId: Int? = null

	@field:SerializedName("created_at")
	val createdAt: String? = null

	@field:SerializedName("type")
	val type: String? = null

	@field:SerializedName("decided_by")
	val decidedBy: Any? = null

	@field:SerializedName("so_number")
	val number: String? = null

	@field:SerializedName("month")
	val month: String? = null

	@field:SerializedName("warehouse_pusat_id")
	val warehousePusatId: String? = null

	@field:SerializedName("day")
	val day: Int? = null

	@field:SerializedName("requester_name")
	val requesterName: String? = null

	@field:SerializedName("status")
	val status: String? = null
}
