package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Province.DataProvince

interface ProvinceInterface {
    fun onSuccessGetProvince(dataProvince: ArrayList<DataProvince?>?)
    fun onErrorGetProvince(msg:String?)
}