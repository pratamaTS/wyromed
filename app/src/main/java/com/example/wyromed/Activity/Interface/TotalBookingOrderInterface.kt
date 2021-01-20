package com.example.wyromed.Activity.Interface

interface TotalBookingOrderInterface {
    fun onSuccessGetTotalBookingOrder(dataTotalBookingOrder: Int?)
    fun onErrorGetTotalBookingOrder(msg:String?)
}