package com.example.wyromed.Ui.Booking

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.wyromed.Base.BaseWyromedActivity
import com.example.wyromed.Data.Connection.ApiServices
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Data.Remote.RemoteRepositoryLocator
import com.example.wyromed.R
import com.example.wyromed.Ui.Booking.RentItem.RentalItemFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BookingActivity : BaseWyromedActivity<BookingContract.Presenter>(), BookingContract.View,
View.OnClickListener {

    companion object {
        const val REQ_VIEW_PRODUCT_DETAIL = 471
        const val RES_BOOKING_CREATED = 472
    }

    override val layoutId: Int = R.layout.activity_booking
    override fun setRentalItemFragment() {
        TODO("Not yet implemented")
    }

    override fun showBaseContent(popBackStack: Boolean) {
        TODO("Not yet implemented")
    }

    override fun bookRentItem(
        bookingOrderHeader: BookingOrderHeader,
        bookingOrderDetails: BookingOrderDetails
    ) {
        TODO("Not yet implemented")
    }

    override fun showMsgBookingSuccess() {
        TODO("Not yet implemented")
    }

    override lateinit var presenter: BookingContract.Presenter

    override var btnBackId: Int = R.id.ic_back

    private lateinit var rootView: View
    private lateinit var baseContainer: View
    private lateinit var fragmentContainer: View

//    private lateinit var rentItemFragment: RentalItemFragment

    private lateinit var btnNext: Button

    override fun init() {
        super.init()

//        presenter = BookingPresenter(
//            RepositoryLocator
//                .getInstance(
//                    RemoteRepositoryLocator
//                        .getInstance(ApiServices.apiService(this)))
//                .bookingRepository)

    }

    override fun setupWidgets() {
        super.setupWidgets()

        rootView = findViewById(R.id.root_view)
        fragmentContainer = findViewById(R.id.fragment_container)

        btnNext = findViewById(R.id.btn_next_book)

        btnNext.setOnClickListener(this)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Fragment
        val f = supportFragmentManager.fragments.indexOfFirst { f -> f is RentalItemFragment }

    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}