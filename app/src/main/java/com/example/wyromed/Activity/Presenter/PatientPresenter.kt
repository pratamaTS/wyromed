package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.PatientInterface
import com.example.wyromed.Activity.Interface.StorePatientInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.PatientBody
import com.example.wyromed.Model.Patient
import com.example.wyromed.Response.Patient.DataPatient
import com.example.wyromed.Response.Patient.ResponsePatient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PatientPresenter(val patientInterface: PatientInterface) {
    fun getAllPatient(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getAllPatient(map)
            .enqueue(object : Callback<ResponsePatient> {

                override fun onFailure(call: Call<ResponsePatient>, t: Throwable) {
                    patientInterface.onErrorGetPatient(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponsePatient>, response: Response<ResponsePatient>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        patientInterface.onSuccessGetPatient(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        patientInterface.onErrorGetPatient(message)
                    }
                }
            })
    }
}