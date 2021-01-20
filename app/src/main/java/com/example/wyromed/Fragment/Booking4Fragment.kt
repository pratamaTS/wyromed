package com.example.wyromed.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Model.Body.BookingOrderDetails
import com.example.wyromed.Model.Body.BookingOrderHeader
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.Model.PurchasedItem
import com.example.wyromed.R
import com.example.wyromedapp.Adapter.ListBookingPItemAdapter
import com.example.wyromedapp.Adapter.ListBookingRItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import kotlin.collections.ArrayList

class Booking4Fragment : Fragment() {
    val finalFragment: Booking5Fragment = Booking5Fragment()
    var purchasedItem: ArrayList<PurchasedItem> = ArrayList()
    var rentalItem: ArrayList<RentalItem> = ArrayList()
    var bookingOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookingOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    var date: TextView? = null
    var time: TextView? = null
    var btnNext4: Button? = null
    var rvListRentalItem: RecyclerView? = null
    var rvListPurchasedItem: RecyclerView? = null
    var adapterR: ListBookingRItemAdapter? = null
    var adapterP: ListBookingPItemAdapter? = null
    var tokenType: String? = null
    var token: String? = null
    val bundle: Bundle = Bundle()
    var dateStart: String? = null
    var startTime: String? = null
    var hospitalName: String? = null
    var patientName: String? = null
    var medrecPatient: String? = null
    var paymentPatient: Boolean? = null
    var totalQuantity: Int? = 0
    var btnBack: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_4, container, false)
        view.context

        //Init View
        btnNext4 = view.findViewById(R.id.btn_next_book4)
        btnBack = view.findViewById(R.id.btn_back_book4)
        rvListRentalItem = view.findViewById(R.id.rv_rental_item4)
        rvListPurchasedItem = view.findViewById(R.id.rv_purchased_item4)

        tokenType = arguments?.getString("token_type")
        token = arguments?.getString("token")
        totalQuantity = arguments?.getInt("total_quantity")
        hospitalName = arguments?.getString("hospital_name")
        patientName = arguments?.getString("patient_name")
        medrecPatient = arguments?.getString("medrec_patient")
        paymentPatient = arguments?.getBoolean("payment_patient")
        val startDate = arguments?.getString("start_date")
        val endDate = arguments?.getString("end_date")
        dateStart = arguments?.getString("start_date_only")
        val dateEnd = arguments?.getString("end_date_only")
        startTime = arguments?.getString("start_time_only")
        val endTime = arguments?.getString("end_time_only")
        rentalItem = arguments?.getParcelableArrayList("rental_item")!!
        if(arguments?.getParcelableArrayList<PurchasedItem>("purchased_item") != null){
            purchasedItem = arguments?.getParcelableArrayList("purchased_item")!!
            bundle.putParcelableArrayList("purchased_item", purchasedItem)
        }
        bookingOrderHeader = arguments?.getParcelable("booking_order_header")!!
        bookingOrderDetails = arguments?.getParcelableArrayList("booking_order_details")!!

        bundle.putString("token_type", tokenType)
        bundle.putString("token", token)
        bundle.putInt("total_quantity", totalQuantity!!)
        bundle.putString("hospital_name", hospitalName)
        bundle.putString("patient_name", patientName)
        bundle.putString("medrec_patient", medrecPatient)
        bundle.putBoolean("payment_patient", paymentPatient!!)
        bundle.putString("start_date", startDate)
        bundle.putString("end_date", endDate)
        bundle.putString("start_date_only", dateStart)
        bundle.putString("end_date_only", dateEnd)
        bundle.putString("start_time_only", startTime)
        bundle.putString("end_time_only", endTime)
        bundle.putParcelable("booking_order_header", bookingOrderHeader)
        bundle.putParcelableArrayList("booking_order_details", bookingOrderDetails)
        bundle.putParcelableArrayList("rental_item", rentalItem)

        Log.d("Start Date Time4", startDate.toString())
        Log.d("End Date Time4", endDate.toString())
        Log.d("Start Date4", dateStart.toString())
        Log.d("End Date4", dateEnd.toString())
        Log.d("Start Time4", startTime.toString())
        Log.d("End Time4", endTime.toString())
        Log.d("list ritem4", rentalItem.toString())
        Log.d("list pitem4", purchasedItem.toString())
        Log.d("Booking Order Header4", bookingOrderHeader.toString())
        Log.d("Booking Order Details4", bookingOrderDetails.toString())

        //RV List Rental Item
        initRvRentalItem()

        //RV List Purchased Item
        initRvPurchasedItem()

        //Button
        initActionButton()

        return view
    }

//    fun onCheckStatus(viewHolder: RecyclerView.ViewHolder?, direction: Int, position: Int){
//        if(viewHolder is ListBookingItemViewHolder){
//            val status: Boolean = listRentalItem[viewHolder.getAdapterPosition()].statusItemBooking
//            if(status == false){
//                dateNotSuccess = listRentalItem[viewHolder.getAdapterPosition()].dateBooking
//                timeNotSuccsess = listRentalItem[viewHolder.getAdapterPosition()].timeBooking
//            }
//        }
//    }

    private fun initRvRentalItem(){
        Log.d("rItem4", rentalItem.toString())
        adapterR = ListBookingRItemAdapter(requireContext(), rentalItem)
        rvListRentalItem?.setLayoutManager(LinearLayoutManager(context))
        rvListRentalItem?.setAdapter(adapterR)
        rvListRentalItem?.setItemAnimator(DefaultItemAnimator())
        rvListRentalItem?.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initRvPurchasedItem(){
        Log.d("pItem4", purchasedItem.toString())
        adapterP = ListBookingPItemAdapter(requireContext(), purchasedItem)
        rvListPurchasedItem?.setLayoutManager(LinearLayoutManager(context))
        rvListPurchasedItem?.setAdapter(adapterP)
        rvListPurchasedItem?.setItemAnimator(DefaultItemAnimator())
        rvListPurchasedItem?.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun initActionButton() {
        btnBack!!.onClick {
            fragmentManager?.popBackStack()
        }

        btnNext4!!.onClick {

            (activity as BookingActivity?)!!.nextStep4()
            finalFragment.arguments = bundle
            fragmentManager?.beginTransaction()?.replace(
                R.id.fragment_booking_container,
                finalFragment
            )
                ?.commit()
        }
    }
}