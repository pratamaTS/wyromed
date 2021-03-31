package com.example.wyromed.Ui.Booking

import com.example.wyromed.Base.BasePresenter
import com.example.wyromed.Base.BaseWyromedPresenter
import com.example.wyromed.Data.Source.BookingDataSource
import java.time.LocalDateTime
import java.time.ZoneId

class BookingPresenter(private val repository: BookingDataSource) : BaseWyromedPresenter<BookingContract.View>(), BookingContract.Presenter {

    override fun onBtnNext() {
        TODO("Not yet implemented")
    }


}