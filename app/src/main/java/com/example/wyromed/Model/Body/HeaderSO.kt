package com.example.wyromed.Model.Body

import com.google.gson.annotations.SerializedName

data class HeaderSO(

	val note: Any? = null,
	val totalPrice: Double? = null,
	val bookingDate: String? = null,
	val endTime: String? = null,
	val hospitalId: Int? = 0,
	val patientNumber: String? = null,
	val bookingId: Int? = 0,
	val startTime: String? = null,
	val bookingNumber: String? = null,
	val patientId: Int? = 0,
	val patientName: String? = null,
	val bpjs: Boolean? = false,
	val hospitalName: String? = null
)
