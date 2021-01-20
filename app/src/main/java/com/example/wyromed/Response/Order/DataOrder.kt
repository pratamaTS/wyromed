package com.example.wyromed.Response.Order

import com.google.gson.annotations.SerializedName

class DataOrder: ResponseOrder() {

    @field:SerializedName("end_date")
    val endDate: String? = null

    @field:SerializedName("note")
    val note: String? = null

    @field:SerializedName("year")
    val year: Int? = null

    @field:SerializedName("weekday")
    val weekday: String? = null

    @field:SerializedName("created_at")
    val createdAt: String? = null

    @field:SerializedName("hospital_id")
    val hospitalId: Int? = null

    @field:SerializedName("patient_number")
    val patientNumber: String? = null

    @field:SerializedName("number")
    val number: String? = null

    @field:SerializedName("month")
    val month: String? = null

    @field:SerializedName("patient_id")
    val patientId: Int? = null

    @field:SerializedName("patient_name")
    val patientName: String? = null

    @field:SerializedName("bpjs")
    val bpjs: String? = null

    @field:SerializedName("total_quantity")
    val totalQuantity: Int? = null

    @field:SerializedName("day")
    val day: Int? = null

    @field:SerializedName("booking_order_id")
    val bookingOrderId: Int? = null

    @field:SerializedName("start_date")
    val startDate: String? = null

    @field:SerializedName("hospital_name")
    val hospitalName: String? = null

    @field:SerializedName("status")
    val status: String? = null
}