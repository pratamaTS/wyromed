package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.StockRequest.DataGetStockRequest

interface StockRequestInterface {
    fun onSuccessGetStockRequest(message: String?, dataReqStock: ArrayList<DataGetStockRequest?>?)
    fun onErrorGetStockRequest(msg:String?)
}