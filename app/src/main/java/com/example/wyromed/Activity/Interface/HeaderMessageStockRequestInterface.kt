package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.DataHeaderMessageStockReq

interface HeaderMessageStockRequestInterface {
    fun onSuccessHeaderMessageSR(message: String?, dataHeaderMessageStockReq: DataHeaderMessageStockReq?)
    fun onErrorHeaderMessageSR(msg:String?)
}