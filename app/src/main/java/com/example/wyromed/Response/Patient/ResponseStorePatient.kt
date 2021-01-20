package com.example.wyromed.Response.Patient

import com.google.gson.annotations.SerializedName

open class ResponseStorePatient(

	@field:SerializedName("data")
	val data: DataStorePatient? = null,

	@field:SerializedName("meta")
	val meta: MetaStorePatient? = null
)


