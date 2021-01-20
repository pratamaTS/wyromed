package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Order.DataOrder

interface OrderedInterface {
    fun onSuccessOrdered(dataOrdered: ArrayList<DataOrder?>?)
    fun onErrorOrdered(msg:String?)
}