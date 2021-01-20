package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Order.DataOrder

interface HistoryBookingInterface {
    fun onSuccessHistoryBooking(dataOrdered: ArrayList<DataOrder?>?)
    fun onErrorHistoryBooking(msg:String?)
}