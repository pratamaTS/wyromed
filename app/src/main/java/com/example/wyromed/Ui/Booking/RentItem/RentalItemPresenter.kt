package com.example.wyromed.Ui.Booking.RentItem

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.wyromed.Base.BaseWyromedPresenter
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Data.Model.Hospital
import com.example.wyromed.Data.Model.RentalItem
import com.example.wyromed.Data.Source.BookingDataSource
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*

class RentalItemPresenter(private val repository: BookingDataSource) : BaseWyromedPresenter<RentalItemContract.View>(), RentalItemContract.Presenter {
    override fun onHospitalLoaded() {
        view?.showLoadingContent(true)

        safeCallPaging(repository.getHospital(), object : Listener<List<Hospital>> {
            override fun onSuccess(data: List<Hospital>?) {
                view?.setDataHospitaltoSpinner(data ?: listOf())
            }
        })
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRentalItemLoaded() {
        view?.showLoadingContent(true)

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val dateNow: String = currentDateTime.format(
            DateTimeFormatter.ofLocalizedDate(
                FormatStyle.LONG
            )
        )

        val timeNow: String = currentDateTime.format(
            DateTimeFormatter.ofLocalizedTime(
                FormatStyle.MEDIUM
            )
        )

        val dateNext: String = "2021-03-27"
        val timeNext: String = "11:00:00"

        val start: String = dateNow + " " + timeNow
        val end: String = dateNext + " " + timeNext

        Log.d("Start", start)
        Log.d("End", end)

        // Query Param Map
        val queryMap: MutableMap<String, String> = HashMap()
        queryMap["start"] = start
        queryMap["end"] = end


        safeCallPaging(repository.getRentalItem(queryMap), object : Listener<List<RentalItem>> {
            override fun onSuccess(data: List<RentalItem>?) {
                view?.setDataRentalItemtoSpinner(data ?: listOf())
            }
        })
    }

    override fun onBtnAdd(
        productID: Int,
        qty: Int,
        itemName: String,
        productUnit: String,
        productEntity: String
    ) {

        // Rental Item
        TODO("Not yet implemented")

    }

    override fun onBtnNext(
        bookingOrderHeader: BookingOrderHeader,
        bookingOrderDetails: BookingOrderDetails
    ) {
        TODO("Not yet implemented")
    }
}