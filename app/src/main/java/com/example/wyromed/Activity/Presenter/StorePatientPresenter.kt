package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.PatientInterface
import com.example.wyromed.Activity.Interface.StorePatientInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Model.Body.PatientBody
import com.example.wyromed.Model.Patient
import com.example.wyromed.Response.Patient.DataPatient
import com.example.wyromed.Response.Patient.ResponsePatient
import com.example.wyromed.Response.Patient.ResponseStorePatient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StorePatientPresenter(val storePatientInterface: StorePatientInterface) {
    fun storePatient(tokenType: String?, token: String?, patient: PatientBody){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .storePatient(map, patient)
            .enqueue(object : Callback<ResponseStorePatient> {

                override fun onFailure(call: Call<ResponseStorePatient>, t: Throwable) {
                    storePatientInterface.onErrorStorePatient(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseStorePatient>, response: Response<ResponseStorePatient>) {
                    val body = response.body()

                    if (response.isSuccessful) {
                        val message: String = body?.meta?.message.toString()
                        storePatientInterface.onSuccessStorePatient(tokenType, token, message)

                        Log.d("Data Body", message)
                    } else {
                        val message = body?.meta?.message
                        storePatientInterface.onErrorStorePatient(message)
                    }
                }
            })
    }
}