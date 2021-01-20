package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.RentalItem.DataRentalItem

interface RentalItemInterface {
    fun onSuccessGetRentalItem(dataRentalItem: ArrayList<DataRentalItem?>?)
    fun onErrorGetRentalItem(msg:String?)
}