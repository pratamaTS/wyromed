package com.example.wyromed.Activity.Presenter

import android.content.Context
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
    fun storePatient(context: Context, patient: PatientBody){

        NetworkConfig.service(context)
            .storePatient(patient)
            .enqueue(object : Callback<ResponseStorePatient> {

                override fun onFailure(call: Call<ResponseStorePatient>, t: Throwable) {
                    storePatientInterface.onErrorStorePatient(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseStorePatient>, response: Response<ResponseStorePatient>) {
                    val body = response.body()

                    if (response.isSuccessful) {
                        val message: String = body?.meta?.message.toString()
                        storePatientInterface.onSuccessStorePatient(message)

                        Log.d("Data Body", message)
                    } else {
                        val message = body?.meta?.message
                        storePatientInterface.onErrorStorePatient(message)
                    }
                }
            })
    }
}