package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.DetailMessageBooking.DataDetailMessageBooking

interface DetailMessageBookingInterface {
    fun onSuccessDetailMessageBooking(message: String?, dataDetailMessageBooking: ArrayList<DataDetailMessageBooking?>?)
    fun onErrorDetailMessageBooking(msg:String?)
}