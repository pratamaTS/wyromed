package com.example.wyromed.Response.SalesOrder

import com.google.gson.annotations.SerializedName

class DataSalesOrder: ResponseSalesOrder() {

    @field:SerializedName("sales_order_id")
    val soID: Int? = null

    @field:SerializedName("date")
    val date: String? = null

    @field:SerializedName("time")
    val time: String? = null

    @field:SerializedName("sales_order_number")
    val soNumber: String? = null

    @field:SerializedName("sales_order_status")
    val soStatus: String? = null

    @field:SerializedName("hospital_name")
    val hospitalName: String? = null

    @field:SerializedName("patient_name")
    val patientName: String? = null

    @field:SerializedName("medrec")
    val medrec: String? = null

    @field:SerializedName("rental_item_name")
    val rentItemName: String? = null

    @field:SerializedName("rental_item_qty")
    val rentItemQty: String? = null

    @field:SerializedName("rental_item_pricexqty")
    val rentItemPricexQty: String? = null

    @field:SerializedName("rental_item_totprice")
    val rentItemTotPrice: String? = null

    @field:SerializedName("purchased_item_name")
    val purchasedItemName: String? = null

    @field:SerializedName("purchased_item_qty")
    val purchasedItemQty: String? = null

    @field:SerializedName("purchased_item_pricexqty")
    val purchasedItemPricexQty: String? = null

    @field:SerializedName("purchased_item_totprice")
    val purchasedItemTotPrice: String? = null

    @field:SerializedName("so_totprice")
    val soTotPrice: String? = null
}