package com.example.wyromed.Response.Patient

import com.google.gson.annotations.SerializedName
class DataStorePatient : ResponseStorePatient() {

	@field:SerializedName("country")
	val country: Any? = null

	@field:SerializedName("address")
	val address: String? = null

	@field:SerializedName("gender")
	val gender: String? = null

	@field:SerializedName("city")
	val city: String? = null

	@field:SerializedName("format_number")
	val formatNumber: String? = null

	@field:SerializedName("date_of_birth")
	val dateOfBirth: String? = null

	@field:SerializedName("hospital_id")
	val hospitalId: Int? = null

	@field:SerializedName("phone")
	val phone: String? = null

	@field:SerializedName("patient_id")
	val patientId: Int? = null

	@field:SerializedName("name")
	val name: String? = null

	@field:SerializedName("is_bpjs")
	val isBpjs: Boolean? = null

	@field:SerializedName("bpjs")
	val bpjs: String? = null

	@field:SerializedName("increment_number")
	val incrementNumber: Int? = null

	@field:SerializedName("state")
	val state: String? = null

	@field:SerializedName("postal_code")
	val postalCode: String? = null

	@field:SerializedName("medic_number")
	val medicNumber: String? = null

	@field:SerializedName("email")
	val email: Any? = null

	@field:SerializedName("hospital_name")
	val hospitalName: String? = null
}
