package com.example.wyromed.Model.Body

import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class PatientBody(var id: String ? = null,
                       var hospital_id: Int? = null,
                       var hospital_name: String? = null,
                       var medic_number: String? = null,
                       var name: String? = null,
                       var date_of_birth: String? = null,
                       var gender: String? = null,
                       var phone: String? = null,
                       var email: String? = null,
                       var address: String? = null,
                       var state: String? = null,
                       var city: String? = null,
                       var postal_code: String? = null,
                       var is_bpjs: Boolean? = null,
                       var bpjs: String? = null): Serializable