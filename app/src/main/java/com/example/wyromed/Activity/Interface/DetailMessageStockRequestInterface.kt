package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.DataDetailMessageStockReq

interface DetailMessageStockRequestInterface {
    fun onSuccessDetailMessageSR(message: String?, dataDetailMessageStockReq: ArrayList<DataDetailMessageStockReq?>?)
    fun onErrorDetailMessageSR(msg:String?)
}