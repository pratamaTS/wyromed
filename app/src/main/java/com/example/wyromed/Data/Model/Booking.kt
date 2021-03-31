package com.example.wyromed.Data.Model

import com.google.gson.annotations.SerializedName

data class Booking(

	@field:SerializedName("end_date")
	val endDate: String? = null,

	@field:SerializedName("note")
	val note: String? = null,

	@field:SerializedName("patient_id")
	val patientId: Int? = null,

	@field:SerializedName("bo_number")
	val boNumber: String? = null,

	@field:SerializedName("patient_name")
	val patientName: String? = null,

	@field:SerializedName("bpjs")
	val bpjs: Boolean? = null,

	@field:SerializedName("total_quantity")
	val totalQuantity: Int? = null,

	@field:SerializedName("increment_number")
	val incrementNumber: Int? = null,

	@field:SerializedName("hospital_id")
	val hospitalId: Int? = null,

	@field:SerializedName("start_date")
	val startDate: String? = null,

	@field:SerializedName("hospital_name")
	val hospitalName: String? = null,

	@field:SerializedName("patient_number")
	val patientNumber: String? = null
)
