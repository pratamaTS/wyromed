package com.example.wyromed.Activity.Interface

import com.example.wyromed.Response.Warehouse.DataWarehouse

interface WarehouseInterface {
    fun onSuccessGetWarehouse(dataWarehouse: ArrayList<DataWarehouse>?)
    fun onErrorGetWarehouse(msg:String?)
}