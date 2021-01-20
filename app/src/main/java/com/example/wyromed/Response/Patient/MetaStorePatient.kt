package com.example.wyromed.Response.Patient

import com.google.gson.annotations.SerializedName

class MetaStorePatient: ResponseStorePatient() {

    @field:SerializedName("ok")
    val ok: Boolean? = null

    @field:SerializedName("message")
    val message: String? = null

    @field:SerializedName("status")
    val status: Int? = null

    @field:SerializedName("error")
    val error: String? = ""
}