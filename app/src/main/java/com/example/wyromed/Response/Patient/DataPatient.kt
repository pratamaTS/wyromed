package com.example.wyromed.Response.Patient

import com.google.gson.annotations.SerializedName

class DataPatient: ResponsePatient() {

    @field:SerializedName("country")
    val country: String? = null

    @field:SerializedName("gender")
    val gender: String? = null

    @field:SerializedName("city")
    val city: String? = null

    @field:SerializedName("date_of_birth")
    val dateOfBirth: Any? = null

    @field:SerializedName("format_number")
    val formatNumber: String? = null

    @field:SerializedName("created_at")
    val createdAt: String? = null

    @field:SerializedName("deleted_by")
    val deletedBy: Any? = null

    @field:SerializedName("updated_at")
    val updatedAt: String? = null

    @field:SerializedName("is_bpjs")
    val isBpjs: String? = null

    @field:SerializedName("id")
    val id: String? = null

    @field:SerializedName("state")
    val state: String? = null

    @field:SerializedName("isdel")
    val isdel: String? = null

    @field:SerializedName("email")
    val email: String? = null

    @field:SerializedName("hospital_name")
    val hospitalName: String? = null

    @field:SerializedName("address")
    val address: String? = null

    @field:SerializedName("hospital_id")
    val hospitalId: String? = null

    @field:SerializedName("created_by")
    val createdBy: String? = null

    @field:SerializedName("deleted_at")
    val deletedAt: Any? = null

    @field:SerializedName("phone")
    val phone: String? = null

    @field:SerializedName("name")
    val name: String? = null

    @field:SerializedName("updated_by")
    val updatedBy: String? = null

    @field:SerializedName("bpjs")
    val bpjs: String? = null

    @field:SerializedName("increment_number")
    val incrementNumber: String? = null

    @field:SerializedName("postal_code")
    val postalCode: String? = null

    @field:SerializedName("medic_number")
    val medicNumber: String? = null

    @field:SerializedName("patient_type")
    val patientType: Int? = null
}