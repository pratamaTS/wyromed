package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Booking.DataBooking

interface StoreSalesOrderInterface {
    fun onSuccessSalesOrder(message: String?)
    fun onErrorSalesOrder(msg:String?)
}