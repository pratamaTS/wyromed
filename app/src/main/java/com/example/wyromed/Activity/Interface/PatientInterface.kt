package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Patient.DataPatient

interface PatientInterface {
    // Get Patient
    fun onSuccessGetPatient(dataPatient: ArrayList<DataPatient?>?)
    fun onErrorGetPatient(msg:String?)
}