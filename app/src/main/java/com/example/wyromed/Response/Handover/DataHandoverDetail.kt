package com.example.wyromed.Response.Handover

import com.google.gson.annotations.SerializedName

class DataHandoverDetail: ResponseHandoverDetail() {

    @field:SerializedName("booking_order_detail_id")
    val bookingOrderDetailId: Int? = null

    @field:SerializedName("quantity")
    val quantity: Int? = null

    @field:SerializedName("product_id")
    val productId: Int? = null

    @field:SerializedName("product_entity")
    val productEntity: String? = null

    @field:SerializedName("product_name")
    val productName: String? = null

    @field:SerializedName("product_unit")
    val productUnit: String? = null

    @field:SerializedName("booking_order_id")
    val bookingOrderId: Int? = null
}