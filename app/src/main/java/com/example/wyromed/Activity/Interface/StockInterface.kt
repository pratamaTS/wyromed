package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.PurchasedItem.DataPurchasedItem
import com.example.wyromed.Response.Stock.DataStock

interface StockInterface {
    fun onSuccessGetStockItem(dataStockItem: ArrayList<DataStock?>?)
    fun onErrorGetStockItem(msg:String?)
}