package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.StockRequest.DataStockRequest

interface StoreStockRequestInterface {
    fun onSuccessStockRequest(message: String?, dataStockReq: DataStockRequest?)
    fun onErrorStockRequest(msg:String?)
}