package com.example.wyromed.Fragment

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Activity.Interface.RentalItemInterface
import com.example.wyromed.Activity.Presenter.RentalItemPresenter
import com.example.wyromed.Adapter.SpinnerDialogRentalItemAdapter
import com.example.wyromed.Model.Body.BookingOrderDetails
import com.example.wyromed.Model.Body.BookingOrderHeader
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.Response.RentalItem.DataRentalItem
import com.example.wyromedapp.Adapter.ListRentalItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import java.util.Calendar.getInstance
import kotlin.collections.ArrayList

class Booking2Fragment : Fragment(), RentalItemInterface {
    val purchasedItemFragment: Booking4Fragment = Booking4Fragment()
    var txtFormDetailDate: TextView? = null
    var txtFormDetailTime: TextView? = null
    var edtHospital: EditText? = null
    var edtStartDate: EditText? = null
    var edtEndDate: EditText? = null
    var edtStartTime: EditText? = null
    var edtEndTime: EditText? = null
    var edtAmount: EditText? = null
    var showStartCalendar: ImageView? = null
    var showEndCalendar: ImageView? = null
    var showStartTime: ImageView? = null
    var showEndTime: ImageView? = null
    var spnRitem: SmartMaterialSpinner<String>?? = null
    var rentalItem: ArrayList<DataRentalItem> = ArrayList()
    var btnAdd: Button? = null
    var btnAddRItem: Button? = null
    var btnNext2: Button? = null
    var rvListRentalItem: RecyclerView? = null
    var listChooseRitem: ArrayList<RentalItem> = ArrayList()
    var bookingOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookingOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    var rentalAdapter: ListRentalItemAdapter? = null
    var mBottomSheetDialog: RoundedBottomSheetDialog? = null
    var productId: Int? = null
    var itemName: String? = null
    var productUnit: String? = null
    var productEntity: String? = null
    var calendar: Calendar? = null
    var year: Int? = null
    var month: Int? = null
    var day: Int? = null
    var timePicker: TimePickerDialog? = null
    var currentTime: Calendar? = null
    var hour: Int? = null
    var minute: Int? = null
    var second: Int? = null
    var dateStartValue: String? = null
    var timeStartValue: String? = null
    var dateEndValue: String? = null
    var timeEndValue: String? = null
    val bundle: Bundle = Bundle()
    var hospitalID: String? = null
    var hospitalName: String? = null
    var patientName: String? = null
    var patientID: String? = null
    var medrecPatient: String? = null
    var paymentPatient: Boolean? = null
    var startDateTime: String? = null
    var endDateTime: String? = null
    var totalQuantity: Int? = 0

    @RequiresApi(Build.VERSION_CODES.O)
    val currentDateTime: LocalDateTime = LocalDateTime.now()

//    companion object {
//        fun newInstance(myList : ArrayList<RentalItem>): Booking2Fragment {
//            val args = Bundle()
//            args.putParcelableArrayList("list_rental_item", myList);
//            val fragment = Booking2Fragment()
//            fragment.arguments = args
//            return fragment
//        }
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_booking_2, container, false)
        view.context

        //Init View
//        edtStartDate = view.findViewById(R.id.edt_start_date)
//        edtStartTime = view.findViewById(R.id.edt_start_time)
//        showStartCalendar = view.findViewById(R.id.show_start_calendar_btn)
//        showStartTime = view.findViewById(R.id.show_start_time_btn)
//        edtEndDate = view.findViewById(R.id.edt_end_date)
//        edtEndTime = view.findViewById(R.id.edt_end_time)
//        showEndCalendar = view.findViewById(R.id.show_end_calendar_btn)
//        showEndTime = view.findViewById(R.id.show_end_time_btn)
        txtFormDetailDate = view.findViewById(R.id.form_detail_date)
        txtFormDetailTime = view.findViewById(R.id.form_detail_time)
        edtHospital = view.findViewById(R.id.edt_hospital)
        btnAddRItem = view.findViewById(R.id.btn_add_item)
        btnNext2 = view.findViewById(R.id.btn_next_book2)
        rvListRentalItem = view.findViewById(R.id.rv_rental_item)
//        hospitalID = arguments?.getString("hospital_id")
//        hospitalName = arguments?.getString("hospital_name")
//        patientID = arguments?.getString("patient_id")
//        patientName = arguments?.getString("patient_name")
//        medrecPatient = arguments?.getString("medrec_patient")
//        paymentPatient = arguments?.getBoolean("payment_patient")

//        bundle.putString("hospital_name", hospitalName)
//        bundle.putString("patient_name", patientName)
//        bundle.putString("medrec_patient", medrecPatient)
//        bundle.putBoolean("payment_patient", paymentPatient!!)

        txtFormDetailDate!!.text = currentDateTime.format(
            DateTimeFormatter.ofLocalizedDate(
                FormatStyle.LONG
            )
        )
        txtFormDetailTime!!.text = currentDateTime.format(
            DateTimeFormatter.ofLocalizedTime(
                FormatStyle.MEDIUM
            )
        )

//        edtStartDate!!.isEnabled = false
//        edtStartTime!!.isEnabled = false
//        edtEndDate!!.isEnabled = false
//        edtEndTime!!.isEnabled = false

        //Bottom Sheet
        mBottomSheetDialog = RoundedBottomSheetDialog(requireContext())
        val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_item, null)

        spnRitem = sheetView.findViewById(R.id.item_id)
        edtAmount = sheetView.findViewById(R.id.edt_amount_ritem)
        btnAdd = sheetView.findViewById(R.id.btn_add)

        //Rental Item
        mBottomSheetDialog!!.setContentView(sheetView)

        // init Calendar
//        initCalendar()

        // init Time Picker
//        initTimePicker()

        // init Button
        initActionButton()

        return view
    }

    fun getAllRentalItem() {
        startDateTime = dateStartValue + " " + timeStartValue
        endDateTime = dateEndValue + " " + timeEndValue

        Log.d("Start Date Time", startDateTime.toString())
        Log.d("End Date Time", endDateTime.toString())

        RentalItemPresenter(this@Booking2Fragment).getAllRentalItem(requireContext(), startDateTime, endDateTime)
    }

//    private fun initCalendar() {
//        //Calendar
//        calendar = getInstance()
//        year = calendar!!.get(Calendar.YEAR)
//        month = calendar!!.get(Calendar.MONTH)
//        day = calendar!!.get(Calendar.DAY_OF_MONTH)
//        val calendarEndDate = getInstance()
//
//        showStartCalendar!!.onClick{
//            val startDatePicker = DatePickerDialog(requireContext(), { view, thisYear, thisMonth, thisDay ->
//                // Display Selected date in TextView
//                month = thisMonth + 1
//                day = thisDay
//                year = thisYear
//                edtStartDate!!.setText("" + thisDay + "/" + month + "/" + thisYear)
//                calendarEndDate.set(thisYear, thisMonth, thisDay)
//
//                dateStartValue =  year.toString() + "-" + month.toString() + "-" + day.toString()
//                Log.d("start date rental", dateStartValue.toString())
//            }, year!!, month!!, day!!)
//
//            startDatePicker.datePicker.minDate = calendar!!.timeInMillis
//            startDatePicker.show()
//        }
//        var newCalendarEndDate = getInstance()
//        var yearEndDate = newCalendarEndDate!!.get(Calendar.YEAR)
//        var monthEndDate = newCalendarEndDate!!.get(Calendar.MONTH)
//        var dayEndDate = newCalendarEndDate!!.get(Calendar.DAY_OF_MONTH)
//
//        showEndCalendar!!.onClick{
//            if(dateStartValue != null && timeStartValue != null) {
//                val endDatePicker = DatePickerDialog(
//                    requireContext(),
//                    { view, thisYearEnd, thisMonthEnd, thisDayEnd ->
//                        // Display Selected date in TextView
//                        monthEndDate = thisMonthEnd + 1
//                        dayEndDate = thisDayEnd
//                        yearEndDate = thisYearEnd
//                        edtEndDate!!.setText("" + thisDayEnd + "/" + monthEndDate + "/" + thisYearEnd)
//
//                        dateEndValue =
//                            yearEndDate.toString() + "-" + monthEndDate.toString() + "-" + dayEndDate.toString()
//                        Log.d("end date rental", dateEndValue.toString())
//                    },
//                    yearEndDate!!,
//                    monthEndDate!!,
//                    dayEndDate!!
//                )
//
//                endDatePicker.datePicker.minDate = calendarEndDate!!.timeInMillis
//                endDatePicker.show()
//            }else if(dateStartValue == null){
//                toast("Please fill the start date first!")
//            }else if(timeStartValue == null){
//                toast("Please fill the start time first!")
//            }else{
//                toast("Please fill the start date & start time first!")
//            }
//        }
//    }
//
//    private fun initTimePicker() {
//        //Time
//        currentTime = getInstance()
//        hour = currentTime!!.get(Calendar.HOUR_OF_DAY)
//        minute = currentTime!!.get(Calendar.MINUTE)
//        second = currentTime!!.get(Calendar.SECOND)
//
//        showStartTime!!.onClick {
//            if(dateStartValue != null) {
//                timePicker =
//                    TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
//                        override fun onTimeSet(
//                            view: TimePicker?,
//                            hourOfDay: Int,
//                            minuteOfDay: Int
//                        ) {
//                            edtStartTime!!.setText(
//                                String.format(
//                                    "%02d : %02d",
//                                    hourOfDay,
//                                    minuteOfDay
//                                )
//                            )
//                            timeStartValue =
//                                String.format("%02d:%02d:%02d", hourOfDay, minuteOfDay, second)
//                            Log.d("end date rental", timeStartValue.toString())
//                        }
//                    }, hour!!, minute!!, true)
//                timePicker!!.show()
//            }else{
//                toast("Please fill the start date first!")
//            }
//        }
//
//        showEndTime!!.onClick {
//            if(dateStartValue != null && timeStartValue != null && dateEndValue != null) {
//                timePicker =
//                    TimePickerDialog(requireContext(), object : TimePickerDialog.OnTimeSetListener {
//                        override fun onTimeSet(
//                            view: TimePicker?,
//                            hourOfDay: Int,
//                            minuteOfDay: Int
//                        ) {
//                            edtEndTime!!.setText(
//                                String.format(
//                                    "%02d : %02d",
//                                    hourOfDay,
//                                    minuteOfDay
//                                )
//                            )
//
//                            timeEndValue =
//                                String.format("%02d:%02d:%02d", hourOfDay, minuteOfDay, second)
//                            Log.d("end date rental", timeEndValue.toString())
//                        }
//                    }, hour!!, minute!!, true)
//                timePicker!!.show()
//            }else if(dateStartValue == null){
//                toast("Please fill the start date first!")
//            }else if(timeStartValue == null){
//                toast("Please fill the start time first!")
//            }else if(dateEndValue == null){
//                toast("Please fill the end date first!")
//            }else{
//                toast("Please fill the start date, start time, end date first!")
//            }
//        }
//    }

    private fun initActionButton() {
        //Button
        btnAddRItem!!.onClick {
                getAllRentalItem()

                mBottomSheetDialog!!.show()
        }

        btnNext2!!.onClick {
            if(listChooseRitem.isNullOrEmpty()){
                toast("There is no rental item")
            }else {
//                bookingOrderHeader!!.hospital_id = hospitalID!!.toInt()
//                bookingOrderHeader!!.patient_id = patientID!!.toInt()
//                bookingOrderHeader!!.start_date = startDateTime
//                bookingOrderHeader!!.end_date = endDateTime
//                bookingOrderHeader!!.bpjs = paymentPatient
//                bookingOrderHeader!!.hospital_name = hospitalName
//                bookingOrderHeader!!.patient_name = patientName
//                bookingOrderHeader!!.patient_number = medrecPatient
                bookingOrderHeader!!.total_quantity = totalQuantity!!.toInt()
                bookingOrderHeader!!.note = "Testing auto generate number"


                bundle.putParcelable("booking_order_header", bookingOrderHeader)

                (activity as BookingActivity?)!!.nextStep()
                purchasedItemFragment.arguments = bundle
                fragmentManager?.beginTransaction()?.replace(
                    R.id.fragment_booking_container,
                    purchasedItemFragment
                )
                    ?.commit()
            }
        }

        btnAdd!!.onClick {
            if (edtAmount!!.text.isNullOrEmpty()) {
                toast("Amount Item cannot be empty")
            } else {
                // Rental Item
                val check = listChooseRitem!!.find {
                    it!!.product_id == productId
                }

                val checkBookingDetails = bookingOrderDetails!!.find {
                    it!!.product_id == productId
                }

                if(listChooseRitem!!.isNotEmpty()){
                    if(check != null) {
                        listChooseRitem!!.find {
                            it!!.product_id == productId
                        }?.quantity = edtAmount!!.text.toString().toInt()
                    }else{
                        listChooseRitem!!.add(
                            RentalItem(
                                productId!!.toInt(),
                                edtAmount!!.text.toString().toInt(),
                                itemName.toString(),
                                productUnit.toString(),
                                productEntity.toString(),
                                dateStartValue,
                                timeStartValue
                            )
                        )
                    }
                }else{
                    listChooseRitem!!.add(
                        RentalItem(
                            productId!!.toInt(),
                            edtAmount!!.text.toString().toInt(),
                            itemName.toString(),
                            productUnit.toString(),
                            productEntity.toString(),
                            dateStartValue,
                            timeStartValue
                        )
                    )
                }

                if(bookingOrderDetails!!.isNotEmpty()){
                    if(checkBookingDetails != null) {
                        bookingOrderDetails!!.find {
                            it!!.product_id == productId
                        }?.quantity = edtAmount!!.text.toString().toInt()
                    }else{
                        bookingOrderDetails!!.add(
                            BookingOrderDetails(
                                productId!!.toInt(),
                                edtAmount!!.text.toString().toInt(),
                                itemName.toString(),
                                productUnit.toString(),
                                productEntity.toString()
                            )
                        )
                    }
                }else{
                    bookingOrderDetails!!.add(
                        BookingOrderDetails(
                            productId!!.toInt(),
                            edtAmount!!.text.toString().toInt(),
                            itemName.toString(),
                            productUnit.toString(),
                            productEntity.toString()
                        )
                    )
                }

                totalQuantity = totalQuantity!! + edtAmount!!.text.toString().toInt()
                Log.d("total quantity", totalQuantity.toString())

                bundle.putInt("total_quantity", totalQuantity!!)
                bundle.putString("start_date_only", dateStartValue)
                bundle.putString("end_date_only", dateEndValue)
                bundle.putString("start_time_only", timeStartValue)
                bundle.putString("end_time_only", timeEndValue)
                bundle.putParcelableArrayList("rental_item", listChooseRitem)
                bundle.putParcelableArrayList("booking_order_details", bookingOrderDetails)

                rentalAdapter = ListRentalItemAdapter(requireContext(), listChooseRitem)
                rvListRentalItem?.setLayoutManager(LinearLayoutManager(context))
                rvListRentalItem?.setAdapter(rentalAdapter)
                rvListRentalItem?.setItemAnimator(DefaultItemAnimator())

                mBottomSheetDialog!!.dismiss()
                edtAmount!!.text.clear()
            }
        }
    }

    override fun onSuccessGetRentalItem(dataRentalItem: ArrayList<DataRentalItem?>?) {
        //Set Value
        rentalItem = dataRentalItem as ArrayList<DataRentalItem>

        val itemList: ArrayList<String> = ArrayList()
        for( i in rentalItem ){
            itemList.add(i.name.toString())
        }

        // Spinner Rental Item
        spnRitem?.setItem(itemList)
        spnRitem?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = rentalItem[position]
                productId = item!!.productId
                itemName = item.name
                productUnit = item.unitName
                productEntity = item.entity
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no item")
            }
        })
    }

    override fun onErrorGetRentalItem(msg: String?) {
        toast(msg ?: "Failed to get rental item").show()
    }

}