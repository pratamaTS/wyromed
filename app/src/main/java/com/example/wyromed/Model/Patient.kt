package com.example.wyromed.Model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import javax.annotation.Generated

@Generated("com.robohorse.robopojogenerator")
class Patient(
	var id: String ? = null,
	var hospital_id: Int? = null,
	var hospital_name: String? = null,
	var medic_number: String? = null,
	var name: String? = null,
	var date_of_birth: LocalDate? = null,
	var gender: String? = null,
	var phone: Int? = null,
	var email: String? = null,
	var address: String? = null,
	var country: String? = null,
	var state: String? = null,
	var city: String? = null,
	var postal_code: Int? = null,
	var is_bpjs: Boolean? = null,
	var bpjs: Int? = null
)
