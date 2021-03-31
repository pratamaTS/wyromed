package com.example.wyromed.Ui.Booking.RentItem

import com.example.wyromed.Base.BasePresenter
import com.example.wyromed.Base.BaseView
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Data.Model.Hospital
import com.example.wyromed.Data.Model.RentalItem
import java.util.*

class RentalItemContract {

    interface View : BaseView<Presenter> {

        fun setDataHospitaltoSpinner(hospital: List<Hospital>)
        fun setDataRentalItemtoSpinner(stock: List<RentalItem>)
        fun showRentalItem(stock: ArrayList<BookingOrderDetails>)
        fun setBookingDatesValue(bookingStartDate: Calendar?, bookingEndDate: Calendar?)

    }

    interface Presenter : BasePresenter {

        fun onHospitalLoaded()
        fun onRentalItemLoaded()
        fun onBtnAdd(productID: Int, qty: Int, itemName: String, productUnit: String, productEntity: String)
        fun onBtnNext(bookingOrderHeader: BookingOrderHeader, bookingOrderDetails: BookingOrderDetails)

    }
}