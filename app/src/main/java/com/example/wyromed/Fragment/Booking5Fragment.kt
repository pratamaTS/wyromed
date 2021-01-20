package com.example.wyromed.Fragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.BaseActivity
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Activity.Interface.BookingInterface
import com.example.wyromed.Activity.MainActivity
import com.example.wyromed.Activity.OrderedActivity
import com.example.wyromed.Activity.Presenter.BookingPresenter
import com.example.wyromed.Activity.Presenter.PurchasedItemPresenter
import com.example.wyromed.Adapter.ListDetailBookingPurchasedItemAdapter
import com.example.wyromed.Adapter.ListDetailBookingRentalItemAdapter
import com.example.wyromed.Model.Body.BookingBody
import com.example.wyromed.Model.Body.BookingOrderDetails
import com.example.wyromed.Model.Body.BookingOrderHeader
import com.example.wyromed.Model.PurchasedItem
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.Response.Booking.DataBooking
import com.example.wyromed.Response.PurchasedItem.DataPurchasedItem
import com.example.wyromedapp.Adapter.ListBookingPItemAdapter
import com.example.wyromedapp.Adapter.ListBookingRItemAdapter
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class Booking5Fragment : Fragment(), BookingInterface {
    var purchasedItem: ArrayList<PurchasedItem> = ArrayList()
    var rentalItem: ArrayList<RentalItem> = ArrayList()
    var bookingOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookingOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    var date: TextView? = null
    var time: TextView? = null
    var tvHospital: TextView? = null
    var tvPatientName: TextView? = null
    var tvMedrec: TextView? = null
    var tvTotalItem: TextView? = null
    var btnBooking: Button? = null
    var rvListRentalItem: RecyclerView? = null
    var rvListPurchasedItem: RecyclerView? = null
    var adapterR: ListDetailBookingRentalItemAdapter? = null
    var adapterP: ListDetailBookingPurchasedItemAdapter? = null
    val bundle: Bundle = Bundle()
    var dateStart: String? = null
    var startTime: String? = null
    var tokenType: String? = null
    var token: String? = null
    var hospitalName: String? = null
    var patientName: String? = null
    var medrecPatient: String? = null
    var paymentPatient: Boolean? = null
    var totalQuantity: Int? = 0
    var bookedItem: DataBooking? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_5, container, false)
        view.context

        //Init View
        btnBooking = view.findViewById(R.id.btn_booking)
        rvListRentalItem = view.findViewById(R.id.rv_detail_rental_item)
        rvListPurchasedItem = view.findViewById(R.id.rv_detail_purchased_item)
        date = view.findViewById(R.id.tv_booking_date)
        time = view.findViewById(R.id.tv_booking_time)
        tvHospital = view.findViewById(R.id.tv_detail_hospital)
        tvPatientName = view.findViewById(R.id.tv_detail_patient_name)
        tvMedrec = view.findViewById(R.id.tv_detail_medical_record)
        tvTotalItem = view.findViewById(R.id.tv_total_item_booking)

        totalQuantity = arguments?.getInt("total_quantity")
        hospitalName = arguments?.getString("hospital_name")
        patientName = arguments?.getString("patient_name")
        medrecPatient = arguments?.getString("medrec_patient")
        paymentPatient = arguments?.getBoolean("payment_patient")
        tokenType = arguments?.getString("token_type")
        token = arguments?.getString("token")
        dateStart = arguments?.getString("start_date_only")
        val dateEnd = arguments?.getString("end_date_only")
        startTime = arguments?.getString("start_time_only")
        val endTime = arguments?.getString("end_time_only")
        rentalItem = arguments?.getParcelableArrayList("rental_item")!!
        if(arguments?.getParcelableArrayList<PurchasedItem>("purchased_item") != null){
            purchasedItem = arguments?.getParcelableArrayList("purchased_item")!!
        }
        bookingOrderHeader = arguments?.getParcelable("booking_order_header")!!
        bookingOrderDetails = arguments?.getParcelableArrayList("booking_order_details")!!

        date!!.text = dateStart.toString()
        time!!.text = startTime.toString()
        tvHospital!!.text = hospitalName.toString()
        tvPatientName!!.text = patientName.toString()
        tvMedrec!!.text = medrecPatient.toString()
        tvTotalItem!!.text = totalQuantity.toString()

        Log.d("Token Type 5", tokenType.toString())
        Log.d("Token 5", token.toString())
        Log.d("Start Date5", dateStart.toString())
        Log.d("End Date5", dateEnd.toString())
        Log.d("Start Time5", startTime.toString())
        Log.d("End Time5", endTime.toString())
        Log.d("list item5", rentalItem.toString())
        Log.d("list item5", purchasedItem.toString())
        Log.d("Booking Order Header5", bookingOrderHeader.toString())
        Log.d("Booking Order Details5", bookingOrderDetails.toString())

        //Button
        initActionButton()

        //RV List Rental Item
        initRvRentalItem()

        //RV List Purchased Item
        initRvPurchasedItem()

        return view
    }

    private fun initRvRentalItem(){
        adapterR = ListDetailBookingRentalItemAdapter(requireContext(), rentalItem)
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
        adapterP = ListDetailBookingPurchasedItemAdapter(requireContext(), purchasedItem)
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
        btnBooking!!.onClick {
            btnBooking!!.isEnabled = false
            btnBooking!!.setBackgroundResource(R.drawable.bg_button_gray)
            BookingPresenter(this@Booking5Fragment).booking(tokenType, token, bookingOrderHeader, bookingOrderDetails)
        }
    }

    override fun onSuccessBooking(message: String?, dataBooking: DataBooking?) {
        bookedItem = dataBooking as DataBooking

        val openDialog = Dialog(requireActivity())
        openDialog.setContentView(R.layout.item_dialog_booking_success)
        val tvNoRequest = openDialog.findViewById<TextView>(R.id.tv_dialog_no_booking)
        val btnOk = openDialog.findViewById<Button>(R.id.dialog_btn_oke_booking)

        // Set Value
        tvNoRequest.text = bookedItem!!.boNumber

        // Open Dialog
        openDialog.setCanceledOnTouchOutside(true)

        btnOk.onClick {
            openDialog.dismiss()
            startActivity<OrderedActivity>(
            OrderedActivity.TAGS.TOKENTYPE to tokenType,
            OrderedActivity.TAGS.TOKEN to token,
            OrderedActivity.TAGS.MESSAGE to message) }

        openDialog.show()
    }

    override fun onErrorBooking(msg: String?) {
        btnBooking!!.isEnabled = true
        btnBooking!!.setBackgroundResource(R.drawable.bg_button_green)
        toast(msg.toString())
    }
}