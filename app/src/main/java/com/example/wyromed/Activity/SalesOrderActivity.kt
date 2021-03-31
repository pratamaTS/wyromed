package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.HeaderMessageBookingInterface
import com.example.wyromed.Activity.Presenter.HeaderMessageBookingPresenter
import com.example.wyromed.Adapter.SalesOrderPurchasedItemAdapter
import com.example.wyromed.Adapter.SalesOrderRentItemAdapter
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.Response.HeaderMessageBooking.DataHeaderMessageBooking
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class SalesOrderActivity : BaseActivity(), HeaderMessageBookingInterface, View.OnClickListener, SalesOrderRentItemAdapter.TotalPriceListenerRental, SalesOrderPurchasedItemAdapter.TotalPriceListenerPurchased {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val ID = "id"
        val HOURSOPS = "hours_ops"
        val MINUTESOPS = "minutes_ops"
        val SECONDSOPS = "seconds_ops"
        val RENTAL = "rental"
        val BMHP = "bmhp"
    }

    var tvNoSO: TextView? = null
    var tvSalesOrderDate: TextView? = null
    var tvHospitalName: TextView? = null
    var tvPatientName: TextView? = null
    var tvMedicalRecord: TextView? = null
    var tvPaymentStatus: TextView? = null
    var back: ImageButton? = null
    var btnSendSO: Button? = null
    var btnFinishSO: Button? = null
    var rvRentItem: RecyclerView? = null
    var rvPurchasedItem: RecyclerView? = null
    var salesOrderRentItemAdapter: SalesOrderRentItemAdapter? = null
    var salesOrderPurchasedItemAdapter: SalesOrderPurchasedItemAdapter? = null
    var salesRentItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var salesPurchasedItemList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var salesOrderDetails: ArrayList<HandoverPurchasedItem> = ArrayList()
    var tvSelectPaymentMethod: TextView? = null
    var tvSelectedDueDate: TextView? = null
    var tvTotalPrice: TextView? = null
    var btnDueDate: ImageButton? = null
    var id: Int = 0
    var totalRental: Int = 0
    var totalPurchased: Int = 0
    var hoursOperation: Long = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var calendar: Calendar? = null
    var year: Int? = null
    var month: Int? = null
    var day: Int? = null
    var dueDate: String? = null
    var selectPayMethod: Boolean = false
    var selectPayDate: Boolean = false
    var salesOrderHeader: SalesOrderHeader = SalesOrderHeader()
    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_order)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        btnSendSO = findViewById(R.id.btn_send_so)
        btnFinishSO = findViewById(R.id.btn_finish_so)
        tvNoSO = findViewById(R.id.tv_no_sales_order)
        tvSalesOrderDate = findViewById(R.id.tv_sales_order_date)
        tvHospitalName = findViewById(R.id.tv_sales_order_hospital)
        tvPatientName = findViewById(R.id.tv_sales_order_patient_name)
        tvMedicalRecord = findViewById(R.id.tv_sales_order_medical_record)
        tvPaymentStatus = findViewById(R.id.tv_sales_order_payment_status)
        rvRentItem = findViewById(R.id.rv_sales_order_rental_item)
        rvPurchasedItem = findViewById(R.id.rv_sales_order_puchased_item)
        tvSelectPaymentMethod = findViewById(R.id.tv_sales_order_payment_method)
        tvSelectedDueDate = findViewById(R.id.tv_sales_order_due_date)
        tvTotalPrice = findViewById(R.id.tv_total_price_sales)
        btnDueDate = findViewById(R.id.btn_open_calendar_due_date)

        id = intent.getIntExtra("id", 0)
        hoursOperation = intent.getLongExtra("hours_ops",0)
        minutesOperation = intent.getLongExtra("minutes_ops",0)
        secondsOperation = intent.getLongExtra("seconds_ops",0)

        salesRentItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        salesPurchasedItemList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        tvSalesOrderDate?.text = current.format(formatter).toString()

        //Set up Adapter Rent Item
        salesOrderRentItemAdapter = SalesOrderRentItemAdapter(this, this, salesRentItemList)
        rvRentItem?.setLayoutManager(LinearLayoutManager(this))
        rvRentItem?.setAdapter(salesOrderRentItemAdapter)
        rvRentItem?.setHasFixedSize(false)

        //Set up Adapter Purchase Item
        salesOrderPurchasedItemAdapter =
            SalesOrderPurchasedItemAdapter(this, this, salesPurchasedItemList)
        rvPurchasedItem?.setLayoutManager(LinearLayoutManager(this))
        rvPurchasedItem?.setAdapter(salesOrderPurchasedItemAdapter)
        rvPurchasedItem?.setHasFixedSize(false)

        getHeaderSO()

        tvSelectPaymentMethod?.setOnClickListener(this)
        tvSelectedDueDate?.setOnClickListener(this)
        btnDueDate?.setOnClickListener(this)
        btnFinishSO?.setOnClickListener(this)
    }

    private fun getHeaderSO() {
        HeaderMessageBookingPresenter(this).getHeaderMessageBooking(this, id)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.ic_back -> finish()
            R.id.tv_sales_order_payment_method -> {
                val bottomSheetDialog =
                    BottomSheetDialog(this@SalesOrderActivity, R.style.BottomSheetDialogTheme)
                val bottomSheetView: View = LayoutInflater.from(applicationContext).inflate(
                    R.layout.layout_bottom_sheet_payment_method,
                    findViewById(R.id.bottom_sheet_container_payment_method)
                )

//                RadioGroup radioGroup = findViewById(R.id.radio_group);
//                RadioButton selectPaymentButton;
//
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                selectPaymentButton = findViewById(selectedId);
//                if (selectedId == 0){
//                    Toast.makeText(SalesOrderActivity.this,"Nothing Selected", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(SalesOrderActivity.this, selectPaymentButton.getText(), Toast.LENGTH_SHORT).show();
//                }
                bottomSheetView.findViewById<View>(R.id.btn_choose_payment_method)
                    .setOnClickListener {
                        tvSelectPaymentMethod?.text = "Cash"
                        selectPayMethod = true
                        bottomSheetDialog.dismiss()
                    }
                bottomSheetDialog.setContentView(bottomSheetView)
                bottomSheetDialog.show()
            }
            R.id.btn_open_calendar_due_date -> {
                //Calendar
                calendar = Calendar.getInstance()
                year = calendar!!.get(Calendar.YEAR)
                month = calendar!!.get(Calendar.MONTH)
                day = calendar!!.get(Calendar.DAY_OF_MONTH)
                val calendarEndDate = Calendar.getInstance()

                btnDueDate!!.onClick{
                    val startDatePicker = DatePickerDialog(this@SalesOrderActivity, { view, thisYear, thisMonth, thisDay ->
                        // Display Selected date in TextView
                        month = thisMonth + 1
                        day = thisDay
                        year = thisYear
                        tvSelectedDueDate!!.setText("" + thisDay + "/" + month + "/" + thisYear)
                        calendarEndDate.set(thisYear, thisMonth, thisDay)

                        dueDate =  year.toString() + "-" + month.toString() + "-" + day.toString()
                    }, year!!, month!!, day!!)

                    startDatePicker.datePicker.minDate = calendar!!.timeInMillis
                    startDatePicker.show()
                    selectPayDate = true
                }
            }
            R.id.btn_send_so ->{
                toast("Email has been sent!")
                finish()
            }
            R.id.btn_finish_so -> {
                when(selectPayMethod) {
                    true ->{
                        when(selectPayDate){
                            true -> {
                                startActivity<AcceptSignatureActivity>()
                            }
                            else -> toast("Select Payment Date First!")
                        }
                    }
                    else -> toast("Select Payment Method First!")
                }
            }
        }
    }

    override fun onSuccessHeaderMessageBooking(
        message: String?,
        dataHeaderMessageBooking: DataHeaderMessageBooking?
    ) {

        tvNoSO?.text = dataHeaderMessageBooking?.number
        tvHospitalName?.text = dataHeaderMessageBooking?.hospitalName
        tvPatientName?.text = dataHeaderMessageBooking?.patientName
        tvMedicalRecord?.text = dataHeaderMessageBooking?.patientNumber

//        when(dataHeaderMessageBooking?.bpjs){
//            "0" -> {
//                tvPaymentStatus?.text = "BPJS"
//                salesOrderHeader.paymentType = false
//            }
//            "1" -> {
//                tvPaymentStatus?.text = "Non-BPJS"
//                salesOrderHeader.paymentType = true
//            }
//        }

//        salesOrderHeader!!.note = dataHeaderMessageBooking!!.note
//        salesOrderHeader!!.totalPrice = 0.0
//        salesOrderHeader!!.bookingDate = dataHeaderMessageBooking!!.startDate
//        salesOrderHeader!!.endTime = dataHeaderMessageBooking!!.endDate
//        salesOrderHeader!!.hospitalId = dataHeaderMessageBooking!!.hospitalId
//        salesOrderHeader!!.patientNumber = dataHeaderMessageBooking!!.patientNumber
//        salesOrderHeader!!.bookingId = dataHeaderMessageBooking!!.bookingOrderId
//        salesOrderHeader!!.startTime = dataHeaderMessageBooking!!.startDate
//        salesOrderHeader!!.bookingNumber = dataHeaderMessageBooking!!.number
//        salesOrderHeader!!.patientId = dataHeaderMessageBooking!!.patientId
//        salesOrderHeader!!.patientName = dataHeaderMessageBooking!!.patientName
//        salesOrderHeader!!.hospitalName = dataHeaderMessageBooking!!.hospitalName
    }

    override fun onErrorHeaderMessageBooking(msg: String?) {
        toast("failed to get data header SO")
    }

    override fun totalPriceListenerRental(totalPrice: Int) {
        totalRental = totalPrice
        tvTotalPrice?.text = numberFormat.format(totalRental + totalPurchased)
    }

    override fun totalPriceListenerPurchased(totalPrice: Int) {
        totalPurchased = totalPrice
        tvTotalPrice?.text = numberFormat.format(totalRental + totalPurchased)
    }
}