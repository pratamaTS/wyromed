package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.QuantityAvailableStock.ResponseTotalQtyStock

interface TotalStockInterface {
    fun onSuccessGetTotalStockItem(dataTotalStockItem: Int?)
    fun onErrorGetTotalStockItem(msg:String?)
}