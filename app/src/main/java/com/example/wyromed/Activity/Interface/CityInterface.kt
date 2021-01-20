package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Province.DataCity

interface CityInterface {
    fun onSuccessGetCity(dataCity: ArrayList<DataCity?>?)
    fun onErrorGetCity(msg:String?)
}