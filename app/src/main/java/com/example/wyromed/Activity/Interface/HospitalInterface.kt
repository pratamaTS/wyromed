package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Hospital.DataHospital

interface HospitalInterface {
    fun onSuccessGetHospital(dataHospital: ArrayList<DataHospital?>?)
    fun onErrorGetHospital(msg:String?)
}