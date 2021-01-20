package com.example.wyromed.Model

import com.google.gson.annotations.SerializedName

class ListRentalItem(
    toString: String,
    toString1: String,
    toString2: String,
    toString3: String,
    toString4: String
) {

    @field:SerializedName("product_id")
    val product_id: String? = null

    @field:SerializedName("quantity")
    val quantity: String? = null

    @field:SerializedName("product_name")
    val product_name: String? = null

    @field:SerializedName("product_unit")
    val product_unit: String? = null

    @field:SerializedName("product_entity")
    val product_entity: String? = null

}