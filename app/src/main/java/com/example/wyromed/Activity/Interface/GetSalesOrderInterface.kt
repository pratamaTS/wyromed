package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.SalesOrder.GetAllSO.DataGetSalesOrder

interface GetSalesOrderInterface {
    fun onSuccessGetSalesOrder(dataSalesOrder: ArrayList<DataGetSalesOrder?>?)
    fun onErrorGetSalesOrder(msg:String?)
}