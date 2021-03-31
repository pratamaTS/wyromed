package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.HospitalInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Hospital.ResponseHospital
import com.example.wyromed.Response.Patient.ResponsePatient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException

class HospitalPresenter(val hospitalInterface: HospitalInterface) {
    fun getAllHospital(context: Context){

        NetworkConfig.service(context)
            .getAllHospital()
            .enqueue(object : Callback<ResponseHospital> {

                override fun onFailure(call: Call<ResponseHospital>, t: Throwable) {
                    try {
                        getAllHospital(context)
                    } catch (e: SocketTimeoutException) {
                        hospitalInterface.onErrorGetHospital(t.localizedMessage)
                    }
                }

                override fun onResponse(call: Call<ResponseHospital>, response: Response<ResponseHospital>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        hospitalInterface.onSuccessGetHospital(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.errorBody().toString()
                        hospitalInterface.onErrorGetHospital(message)
                    }
                }
            })
    }
}