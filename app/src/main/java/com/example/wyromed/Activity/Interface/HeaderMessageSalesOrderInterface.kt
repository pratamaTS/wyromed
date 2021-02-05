package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Header.DataHeaderMessageSalesOrder

interface HeaderMessageSalesOrderInterface {
    fun onSuccessHeaderMessageSO(message: String?, dataHeaderMessageSalesOrder: DataHeaderMessageSalesOrder?)
    fun onErrorHeaderMessageSO(msg:String?)
}