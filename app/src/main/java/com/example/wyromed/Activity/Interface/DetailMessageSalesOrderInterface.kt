package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.DataDetailMessageSalesOrder

interface DetailMessageSalesOrderInterface {
    fun onSuccessDetailMessageSO(message: String?, dataDetailMessageSalesOrder: ArrayList<DataDetailMessageSalesOrder?>?)
    fun onErrorDetailMessageSO(msg:String?)
}