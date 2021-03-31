package com.example.wyromed.Ui.Booking

import com.example.wyromed.Base.BasePresenter
import com.example.wyromed.Base.BaseView
import com.example.wyromed.Data.Model.Booking
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader

class BookingContract {

    interface View : BaseView<Presenter> {

        fun setRentalItemFragment()
        fun showBaseContent(popBackStack: Boolean = true)
        fun bookRentItem(bookingOrderHeader: BookingOrderHeader, bookingOrderDetails: BookingOrderDetails)

        fun showMsgBookingSuccess()

    }

    interface Presenter : BasePresenter {

        fun onBtnNext()

    }
}