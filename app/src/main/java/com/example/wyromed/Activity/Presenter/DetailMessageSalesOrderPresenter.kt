package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.DetailMessageSalesOrderInterface
import com.example.wyromed.Activity.Interface.DetailMessageStockRequestInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Response.StockRequest.DetailMessageStockReq.Detail.ResponseDetailMessageSalesOrder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailMessageSalesOrderPresenter(val detailMessageSalesOrderInterface: DetailMessageSalesOrderInterface) {
    fun getDetailMessageSO(
        context: Context,
        id: Int
    ){
        val url: String = "stockrequest/details/" + id

        NetworkConfig.service(context)
            .getDetailMessageSO(url)
            .enqueue(object : Callback<ResponseDetailMessageSalesOrder> {

                override fun onFailure(call: Call<ResponseDetailMessageSalesOrder>, t: Throwable) {
                    detailMessageSalesOrderInterface.onErrorDetailMessageSO(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseDetailMessageSalesOrder>,
                    response: Response<ResponseDetailMessageSalesOrder>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        detailMessageSalesOrderInterface.onSuccessDetailMessageSO(message, data)
                    } else {
                        detailMessageSalesOrderInterface.onErrorDetailMessageSO(error)
                    }
                }
            })
    }
}