package com.example.wyromed.Activity.Presenter

import android.content.Context
import android.util.Log
import com.example.wyromed.Activity.Interface.WarehouseInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.Warehouse.ResponseWarehouse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WarehousePresenter(val warehouseInterface: WarehouseInterface) {
    fun getWarehouse(context: Context){

        NetworkConfig.service(context)
            .getWarehouse()
            .enqueue(object : Callback<ResponseWarehouse> {

                override fun onFailure(call: Call<ResponseWarehouse>, t: Throwable) {
                    warehouseInterface.onErrorGetWarehouse(t.localizedMessage)
                }

                override fun onResponse(call: Call<ResponseWarehouse>, response: Response<ResponseWarehouse>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        warehouseInterface.onSuccessGetWarehouse(data)

                        Log.d("Data Body", data.toString())
                    } else {
                        val message = response.errorBody().toString()
                        warehouseInterface.onErrorGetWarehouse(message)
                    }
                }
            })
    }
}