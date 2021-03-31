package com.example.wyromed.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.ListDetailBookingRentalItemAdapter
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Booking5Fragment : Fragment() {
    var bookingOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookingOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    var date: TextView? = null
    var time: TextView? = null
    var tvHospital: TextView? = null
    var tvTotalItem: TextView? = null
    var rvListRentalItem: RecyclerView? = null
    var adapterR: ListDetailBookingRentalItemAdapter? = null
    val bundle: Bundle = Bundle()
    var hospitalName: String? = null
    var patientName: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_5, container, false)
        view.context

        //Init View
        rvListRentalItem = view.findViewById(R.id.rv_detail_rental_item)
        date = view.findViewById(R.id.tv_booking_date)
        time = view.findViewById(R.id.tv_booking_time)
//        tvHospital = view.findViewById(R.id.tv_detail_hospital)
        tvTotalItem = view.findViewById(R.id.tv_total_item_booking)

        bookingOrderHeader = arguments?.getParcelable("booking_order_header")!!
        bookingOrderDetails = arguments?.getParcelableArrayList("booking_order_details")!!

        val dates = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(bookingOrderHeader.start_date)
        val newDate = SimpleDateFormat("dd MMM yyyy").format(dates)
        val newTime = SimpleDateFormat("HH:mm:ss").format(dates)

        date!!.text = newDate
        time!!.text = newTime + " WIB"
        tvTotalItem!!.text = bookingOrderHeader.total_quantity.toString()

        Log.d("Booking Order Header5", bookingOrderHeader.toString())
        Log.d("Booking Order Details5", bookingOrderDetails.toString())

        //RV List Rental Item
        initRvRentalItem()

        return view
    }

    private fun initRvRentalItem(){
        adapterR = ListDetailBookingRentalItemAdapter(requireContext(), bookingOrderDetails)
        rvListRentalItem?.setLayoutManager(LinearLayoutManager(context))
        rvListRentalItem?.setAdapter(adapterR)
        rvListRentalItem?.setItemAnimator(DefaultItemAnimator())
    }
}