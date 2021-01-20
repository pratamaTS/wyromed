package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.StockRequest.DataGetStockRequest

interface HistoryStockRequestInterface {
    fun onSuccessGetHistoryStockRequest(message: String?, dataReqStock: ArrayList<DataGetStockRequest?>?)
    fun onErrorGetHistoryStockRequest(msg:String?)
}