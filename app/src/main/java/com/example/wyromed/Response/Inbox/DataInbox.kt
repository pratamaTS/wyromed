package com.example.wyromed.Response.Inbox

import com.google.gson.annotations.SerializedName

class DataInbox: ResponseInbox() {

	@field:SerializedName("inbox_id")
	val inboxId: Int? = null

	@field:SerializedName("year")
	val year: Int? = null

	@field:SerializedName("weekday")
	val weekday: String? = null

	@field:SerializedName("description")
	val description: String? = null

	@field:SerializedName("created_at")
	val createdAt: String? = null

	@field:SerializedName("title")
	val title: String? = null

	@field:SerializedName("message")
	val message: Any? = null

	@field:SerializedName("soa_id")
	val soaId: Int? = null

	@field:SerializedName("month")
	val month: String? = null

	@field:SerializedName("time")
	val time: String? = null

	@field:SerializedName("bo_id")
	val boId: Int? = null

	@field:SerializedName("transaction_number")
	val transactionNumber: String? = null

	@field:SerializedName("request_id")
	val requestId: Int? = null

	@field:SerializedName("day")
	val day: Int? = null
}
