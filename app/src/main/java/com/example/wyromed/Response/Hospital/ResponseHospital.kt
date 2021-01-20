package com.example.wyromed.Response.Hospital

import com.google.gson.annotations.SerializedName
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
open class ResponseHospital {

	@field:SerializedName("data")
	val data: ArrayList<DataHospital?> = ArrayList()

	@field:SerializedName("meta")
	val meta: MetaHospital? = null
}