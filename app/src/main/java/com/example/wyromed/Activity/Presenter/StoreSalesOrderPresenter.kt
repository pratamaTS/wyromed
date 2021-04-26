package com.example.wyromed.Activity.Presenter

import android.content.Context
import com.example.wyromed.Activity.Interface.BookingInterface
import com.example.wyromed.Activity.Interface.StoreSalesOrderInterface
import com.example.wyromed.Api.NetworkConfig
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Data.Model.SalesOrderDetail
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Response.Booking.ResponseBooking
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.HashMap


class StoreSalesOrderPresenter(val storeSalesOrderInterface: StoreSalesOrderInterface) {
    fun createSO(
        context: Context,
        salesOrderHeader: SalesOrderHeader,
        salesOrderDetail: ArrayList<SalesOrderDetail>,
        nurseSign: MultipartBody.Part,
        doctorSign: MultipartBody.Part,
        sellerSign: MultipartBody.Part
    ){

        val bodyMap = HashMap<String, Any>()

        // Body
        bodyMap.put("sales_order_details", salesOrderDetail)
        bodyMap.put("sales_order_header", salesOrderHeader)

        NetworkConfig.service(context)
            .storeSalesOrder(doctorSign, sellerSign, nurseSign, bodyMap)
            .enqueue(object : Callback<ResponseBooking> {

                override fun onFailure(call: Call<ResponseBooking>, t: Throwable) {
                    storeSalesOrderInterface.onErrorSalesOrder(t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseBooking>,
                    response: Response<ResponseBooking>
                ) {
                    val body = response.body()
                    val errorBody = response.errorBody()
                    val data = body?.data
                    val message = body?.meta?.message
                    val error = errorBody?.string()

                    if (response.isSuccessful) {
                        storeSalesOrderInterface.onSuccessSalesOrder(message)
                    } else {
                        storeSalesOrderInterface.onErrorSalesOrder(error)
                    }
                }
            })
    }
}