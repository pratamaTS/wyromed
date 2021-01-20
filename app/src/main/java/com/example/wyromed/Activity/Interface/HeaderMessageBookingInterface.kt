package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.HeaderMessageBooking.DataHeaderMessageBooking

interface HeaderMessageBookingInterface {
    fun onSuccessHeaderMessageBooking(message: String?, dataHeaderMessageBooking: DataHeaderMessageBooking?)
    fun onErrorHeaderMessageBooking(msg:String?)
}