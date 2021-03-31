package com.example.wyromed.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.Model.PurchasedItem
import com.example.wyromed.R
import com.example.wyromedapp.Adapter.ListBookingPItemAdapter
import com.example.wyromedapp.Adapter.ListBookingRItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import kotlin.collections.ArrayList

class Booking4Fragment : Fragment() {
    var rentalItem: ArrayList<RentalItem> = ArrayList()
    var bookingOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookingOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    var date: TextView? = null
    var time: TextView? = null
    var btnNext4: Button? = null
    var rvListRentalItem: RecyclerView? = null
    var adapterR: ListBookingRItemAdapter? = null
    var adapterP: ListBookingPItemAdapter? = null
    var hospitalName: String? = null
    var patientName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_4, container, false)
        view.context

        //Init View
        rvListRentalItem = view.findViewById(R.id.rv_rental_item4)

        bookingOrderHeader = arguments?.getParcelable("booking_order_header")!!
        bookingOrderDetails = arguments?.getParcelableArrayList("booking_order_details")!!

        Log.d("Booking Order Header4", bookingOrderHeader.toString())
        Log.d("Booking Order Details4", bookingOrderDetails.toString())

        //RV List Rental Item
        initRvRentalItem()

        return view
    }

    private fun initRvRentalItem(){
        Log.d("rItem4", rentalItem.toString())
        adapterR = ListBookingRItemAdapter(requireContext(), bookingOrderDetails)
        rvListRentalItem?.setLayoutManager(LinearLayoutManager(context))
        rvListRentalItem?.setAdapter(adapterR)
        rvListRentalItem?.setItemAnimator(DefaultItemAnimator())
    }
}