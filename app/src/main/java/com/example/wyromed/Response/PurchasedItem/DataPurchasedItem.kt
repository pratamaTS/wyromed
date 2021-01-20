package com.example.wyromed.Response.PurchasedItem

import com.google.gson.annotations.SerializedName

class DataPurchasedItem: ResponsePurchasedItem() {

    @field:SerializedName("unit_name")
    val unitName: String? = null

    @field:SerializedName("product_id")
    val productId: Int? = null

    @field:SerializedName("name")
    val name: String? = null

    @field:SerializedName("qty_allocated")
    val qtyAllocated: Int? = null

    @field:SerializedName("stock_available")
    val stockAvailable: Int? = null

    @field:SerializedName("entity")
    val entity: String? = null

    @field:SerializedName("total_stock")
    val totalStock: Int? = null
}