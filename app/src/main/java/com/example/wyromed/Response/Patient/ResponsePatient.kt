package com.example.wyromed.Response.Patient

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponsePatient {
	@field:SerializedName("data")
	val data: ArrayList<DataPatient?> = ArrayList()

	@field:SerializedName("meta")
	val meta: MetaPatient? = null
}