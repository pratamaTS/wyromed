package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Handover.DataHandoverHeader

interface HandoverHeaderBookingInterface {
    fun onSuccessHandoverHeaderBooking(message: String?, dataHeaderHandOver: DataHandoverHeader?)
    fun onErrorHandoverHeaderBooking(msg:String?)
}