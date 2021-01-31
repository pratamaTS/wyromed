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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class PatientPresenter(val patientInterface: PatientInterface) {
    fun getAllPatient(context: Context){

        NetworkConfig.service(context)
            .getAllPatient()
            .enqueue(object : Callback<ResponsePatient> {

                override fun onFailure(call: Call<ResponsePatient>, t: Throwable) {
                    try {
                        getAllPatient(context)
                    } catch (e: SocketTimeoutException) {
                        patientInterface.onErrorGetPatient(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponsePatient>, response: Response<ResponsePatient>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        patientInterface.onSuccessGetPatient(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        try {
                            getAllPatient(context)
                        } catch (e: SocketTimeoutException) {
                            patientInterface.onErrorGetPatient(message)
                        }
                    }
                }
            })
    }
}