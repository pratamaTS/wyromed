package com.example.wyromed.Response.StockRequest

import com.google.gson.annotations.SerializedName

class DataStockRequest: ResponseStockRequest() {

    @field:SerializedName("transaction_date")
    val transactionDate: String? = null

    @field:SerializedName("note")
    val note: String? = null

    @field:SerializedName("number")
    val number: String? = null

    @field:SerializedName("warehouse_pusat_id")
    val warehousePusatId: Int? = null

    @field:SerializedName("increment_number")
    val incrementNumber: Int? = null

    @field:SerializedName("type")
    val type: String? = null

    @field:SerializedName("requester_name")
    val requesterName: String? = null
}