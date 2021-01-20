package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Handover.DataHandoverDetail

interface HandoverDetailBookingInterface {
    fun onSuccessHandoverDetailBooking(message: String?, dataDetailHandOver: ArrayList<DataHandoverDetail?>?)
    fun onErrorHandoverDetailBooking(msg:String?)
}