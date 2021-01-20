package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Booking.DataBooking

interface BookingInterface {
    fun onSuccessBooking(message: String?, dataBooking: DataBooking?)
    fun onErrorBooking(msg:String?)
}