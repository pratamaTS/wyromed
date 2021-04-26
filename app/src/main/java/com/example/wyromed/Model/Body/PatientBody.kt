package com.example.wyromed.Model.Body

import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class PatientBody(var hospital_id: Int? = null,
                       var hospital_name: String? = null,
                       var medic_number: String? = null,
                       var name: String? = null,
                       var patient_type: Int? = null,
                       var is_bpjs: Boolean? = null,
                       var bpjs: String? = null): Serializable