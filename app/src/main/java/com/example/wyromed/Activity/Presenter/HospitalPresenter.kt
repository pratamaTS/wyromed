package com.example.wyromed.Activity.Presenter

import android.util.Log
import com.example.wyromed.Activity.Interface.HospitalInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Hospital.ResponseHospital
import com.example.wyromed.Response.Patient.ResponsePatient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HospitalPresenter(val hospitalInterface: HospitalInterface) {
    fun getAllHospital(tokenType: String?, token: String?){
        //Declare Dynamic Headers
        val tokenHeader: String = tokenType.toString() +" "+ token.toString()
        val map: MutableMap<String, String> = HashMap()
        map["Authorization"] = tokenHeader
        map["Host"] = "absdigital.id"


        NetworkConfig.service()
            .getAllHospital(map)
            .enqueue(object : Callback<ResponseHospital> {

                override fun onFailure(call: Call<ResponseHospital>, t: Throwable) {
                    hospitalInterface.onErrorGetHospital(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseHospital>, response: Response<ResponseHospital>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        hospitalInterface.onSuccessGetHospital(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.body()?.meta?.message
                        hospitalInterface.onErrorGetHospital(message)
                    }
                }
            })
    }
}