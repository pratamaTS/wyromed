package com.example.wyromed.Response.RentalItem

import com.google.gson.annotations.SerializedName

class DataRentalItem: ResponseRentalItem() {

    @field:SerializedName("unit_name")
    val unitName: String? = null

    @field:SerializedName("rental_available")
    val rentalAvailable: Int? = null

    @field:SerializedName("product_id")
    val productId: Int? = null

    @field:SerializedName("name")
    val name: String? = null

    @field:SerializedName("qty_bo")
    val qtyBo: Int? = null

    @field:SerializedName("qty_allocated")
    val qtyAllocated: Int? = null

    @field:SerializedName("entity")
    val entity: String? = null

    @field:SerializedName("total_stock")
    val totalStock: Int? = null
}