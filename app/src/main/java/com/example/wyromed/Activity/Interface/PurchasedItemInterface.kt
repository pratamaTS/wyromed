package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.PurchasedItem.DataPurchasedItem

interface PurchasedItemInterface {
    fun onSuccessGetPurchasedItem(dataPurchasedItem: ArrayList<DataPurchasedItem?>?)
    fun onErrorGetPurchasedItem(msg:String?)
}