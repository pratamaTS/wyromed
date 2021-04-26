package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.example.wyromed.Activity.Interface.HeaderMessageBookingInterface
import com.example.wyromed.Activity.Interface.PatientInterface
import com.example.wyromed.Activity.Presenter.HeaderMessageBookingPresenter
import com.example.wyromed.Activity.Presenter.PatientPresenter
import com.example.wyromed.Adapter.SalesOrderPurchasedItemAdapter
import com.example.wyromed.Adapter.SalesOrderRentItemAdapter
import com.example.wyromed.Data.Model.SalesOrderDetail
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.Response.HeaderMessageBooking.DataHeaderMessageBooking
import com.example.wyromed.Response.Patient.DataPatient
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class SalesOrderActivity : BaseActivity(), View.OnClickListener, SalesOrderRentItemAdapter.TotalPriceListenerRental, SalesOrderPurchasedItemAdapter.TotalPriceListenerPurchased, PatientInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val HOURSOPS = "hours_ops"
        val MINUTESOPS = "minutes_ops"
        val SECONDSOPS = "seconds_ops"
        val RENTAL = "rental"
        val BMHP = "bmhp"
        val SOHEADER = "so_header"
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
    var salesOrderDetails: ArrayList<SalesOrderDetail> = ArrayList()
    var tvSelectPaymentMethod: TextView? = null
    var tvSelectedDueDate: TextView? = null
    var tvTotalPrice: TextView? = null
    var btnDueDate: ImageButton? = null
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
    val patientName: String = ""
    var medrecPatient: String = ""
    val paymentStatus: String = ""
    var listPatient: ArrayList<String> = ArrayList()
    var patient: ArrayList<DataPatient> = ArrayList()

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

        hoursOperation = intent.getLongExtra("hours_ops", 0)
        minutesOperation = intent.getLongExtra("minutes_ops", 0)
        secondsOperation = intent.getLongExtra("seconds_ops", 0)
        salesOrderHeader = intent.getParcelableExtra("so_header")!!

        Log.d("head first", salesOrderHeader.toString())

        salesRentItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        salesPurchasedItemList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        tvNoSO?.text = salesOrderHeader?.bookingNumber
        tvHospitalName?.text = salesOrderHeader?.hospitalName

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

        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val endDate: String = currentDateTime.format(
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        )

        salesOrderHeader.endTime = endDate

        loadData()

        tvSelectPaymentMethod?.setOnClickListener(this)
        tvPatientName?.setOnClickListener(this)
        tvSelectedDueDate?.setOnClickListener(this)
        btnDueDate?.setOnClickListener(this)
        btnFinishSO?.setOnClickListener(this)
    }

    private fun loadData() {
        PatientPresenter(this).getAllPatient(this)
    }

    private fun createSODetail() {
        if(salesRentItemList.isNotEmpty()){
            for (dataRental in salesRentItemList){
                salesOrderDetails.add(SalesOrderDetail(
                    totalRental.toDouble(),
                    dataRental.quantity,
                    dataRental.product_id,
                    5,
                    dataRental.product_entity,
                    5000000.toDouble(),
                    dataRental.product_name,
                    dataRental.product_unit
                ))
            }
            if(salesPurchasedItemList.isNotEmpty()){
                for (dataPurchased in salesPurchasedItemList){
                    salesOrderDetails.add(SalesOrderDetail(
                        totalRental.toDouble(),
                        dataPurchased.quantity,
                        dataPurchased.productId,
                        5,
                        dataPurchased.entity,
                        5000000.toDouble(),
                        dataPurchased.name,
                        dataPurchased.unitName
                    ))
                }
            }
        }else if(salesPurchasedItemList.isNotEmpty()){
            for (dataPurchased in salesPurchasedItemList){
                salesOrderDetails.add(SalesOrderDetail(
                    totalRental.toDouble(),
                    dataPurchased.quantity,
                    dataPurchased.productId,
                    5,
                    dataPurchased.entity,
                    5000000.toDouble(),
                    dataPurchased.name,
                    dataPurchased.unitName
                ))
            }
        }
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_sales_order_patient_name -> {
                val bottomSheetDialogPatient =
                    RoundedBottomSheetDialog(this)
                val bottomSheetViewPatient: View = layoutInflater.inflate(
                    R.layout.layout_bottom_sheet_patient, null)

                var patientName: String = ""
                var patientType: Int = 0
                var patientId: Int = 0
                val spinnerPatient =
                    bottomSheetViewPatient.findViewById<SmartMaterialSpinner<String>>(R.id.patient_so_id)
                val medrec: TextView = bottomSheetViewPatient.findViewById(R.id.medical_record_so)
                val paymentStatus: TextView =
                    bottomSheetViewPatient.findViewById(R.id.payment_status_so)

                bottomSheetViewPatient.findViewById<View>(R.id.btn_add_so_patient)
                    .setOnClickListener {
                        startActivity<FormPatientActivity>(
                            FormPatientActivity.TAGS.HOURSOPS to hoursOperation,
                            FormPatientActivity.TAGS.MINUTESOPS to minutesOperation,
                            FormPatientActivity.TAGS.SECONDSOPS to secondsOperation,
                            FormPatientActivity.TAGS.RENTAL to salesRentItemList,
                            FormPatientActivity.TAGS.BMHP to salesPurchasedItemList,
                            FormPatientActivity.TAGS.SOHEADER to salesOrderHeader
                        )
                        finish()
                    }

                bottomSheetViewPatient.findViewById<View>(R.id.btn_save_patient)
                    .setOnClickListener {
                        tvPatientName?.text = patientName
                        tvMedicalRecord?.text = medrec.text.toString()
                        tvPaymentStatus?.text = paymentStatus.text.toString()

                        salesOrderHeader?.patientType = patientType
                        salesOrderHeader?.patientId = patientId
                        salesOrderHeader?.patientName = patientName
                        salesOrderHeader?.patientNumber = medrec.text.toString()
                        bottomSheetDialogPatient.dismiss()
                    }

                bottomSheetDialogPatient.setContentView(bottomSheetViewPatient)
                bottomSheetDialogPatient.show()

//                Spinner Patient
                spinnerPatient.setItem(listPatient)
                spinnerPatient.setOnItemSelectedListener(object :
                    AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        adapterView: AdapterView<*>?,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        val item = patient[position]
                        patientName = item.name.toString()
                        medrec.text = item.medicNumber.toString()
                        patientType = item.patientType!!
                        patientId = item.id!!.toInt()

                        when (item.patientType) {
                            1 -> paymentStatus.text = "Umur"
                            2 -> paymentStatus.text = "Asuransi Umur"
                            3 -> paymentStatus.text = "Asuransi BPJS"
                        }
                    }

                    override fun onNothingSelected(adapterView: AdapterView<*>?) {
                        toast("There is no patient")
                    }
                })
            }
            R.id.ic_back -> finish()
            R.id.tv_sales_order_payment_method -> {
                val bottomSheetDialog =
                    BottomSheetDialog(this@SalesOrderActivity, R.style.BottomSheetDialogTheme)
                val bottomSheetView: View = LayoutInflater.from(applicationContext).inflate(
                    R.layout.layout_bottom_sheet_payment_method,
                    findViewById(R.id.bottom_sheet_container_payment_method)
                )

                val rgPaymentType = bottomSheetView.findViewById<RadioGroup>(R.id.rg_payment_type)

                bottomSheetView.findViewById<View>(R.id.btn_choose_payment_method)
                    .setOnClickListener {
                        selectPayMethod = true
                        when (rgPaymentType.checkedRadioButtonId) {
                            R.id.rb_cash -> {
                                tvSelectPaymentMethod?.text = "Cash"
                                salesOrderHeader?.paymentType = "Cash"
                            }
                            R.id.rb_credit -> {
                                tvSelectPaymentMethod?.text = "Credit"
                                salesOrderHeader?.paymentType = "Credit"
                            }
                        }
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

                val startDatePicker = DatePickerDialog(
                    this@SalesOrderActivity,
                    { view, thisYear, thisMonth, thisDay ->
                        // Display Selected date in TextView
                        month = thisMonth + 1
                        day = thisDay
                        year = thisYear
                        tvSelectedDueDate!!.setText("" + thisDay + "/" + month + "/" + thisYear)
                        calendarEndDate.set(thisYear, thisMonth, thisDay)

                        dueDate =
                            year.toString() + "-" + month.toString() + "-" + day.toString()
                    },
                    year!!,
                    month!!,
                    day!!
                )

                startDatePicker.datePicker.minDate = calendar!!.timeInMillis
                startDatePicker.show()
                salesOrderHeader.soDueDate = dueDate + " 23:59:59"
                selectPayDate = true
            }
            R.id.tv_sales_order_due_date -> {
                calendar = Calendar.getInstance()
                year = calendar!!.get(Calendar.YEAR)
                month = calendar!!.get(Calendar.MONTH)
                day = calendar!!.get(Calendar.DAY_OF_MONTH)
                val calendarEndDate = Calendar.getInstance()

                val startDatePicker = DatePickerDialog(
                    this@SalesOrderActivity,
                    { view, thisYear, thisMonth, thisDay ->
                        // Display Selected date in TextView
                        month = thisMonth + 1
                        day = thisDay
                        year = thisYear
                        tvSelectedDueDate!!.setText("" + thisDay + "/" + month + "/" + thisYear)
                        calendarEndDate.set(thisYear, thisMonth, thisDay)

                        dueDate =
                            year.toString() + "-" + month.toString() + "-" + day.toString() + " 23:59:59"

                        salesOrderHeader.soDueDate = dueDate
                    },
                    year!!,
                    month!!,
                    day!!
                )

                startDatePicker.datePicker.minDate = calendar!!.timeInMillis
                startDatePicker.show()
                selectPayDate = true
            }
            R.id.btn_send_so -> {
                toast("Email has been sent!")
                finish()
            }
            R.id.btn_finish_so -> {
                when (selectPayMethod) {
                    true -> {
                        when (selectPayDate) {
                            true -> {
                                salesOrderHeader.note = "done"

                                createSODetail()

                                Log.d("head", salesOrderHeader.toString())
                                Log.d("details", salesOrderDetails.toString())

                                startActivity<AcceptSignatureActivity>(
                                    AcceptSignatureActivity.TAGS.SODETAIL to salesOrderDetails,
                                    AcceptSignatureActivity.TAGS.SOHEADER to salesOrderHeader
                                )
                                finish()
                            }
                            else -> toast("Select Payment Date First!")
                        }
                    }
                    else -> toast("Select Payment Method First!")
                }
            }
        }
    }

    override fun totalPriceListenerRental(totalPrice: Int) {
        totalRental = totalPrice
        tvTotalPrice?.text = numberFormat.format(totalRental + totalPurchased)
        val totPrice: Double = (totalRental + totalPurchased).toDouble()
        salesOrderHeader?.totalPrice = totPrice
    }

    override fun totalPriceListenerPurchased(totalPrice: Int) {
        totalPurchased = totalPrice
        tvTotalPrice?.text = numberFormat.format(totalRental + totalPurchased)
        val totPrice: Double = (totalRental + totalPurchased).toDouble()
        salesOrderHeader?.totalPrice = totPrice
    }

    override fun onSuccessGetPatient(dataPatient: ArrayList<DataPatient?>?) {
        //Set Value
        patient = dataPatient as ArrayList<DataPatient>
        if (dataPatient != null) {
            for( p in dataPatient ){
                listPatient.add(p?.name.toString())
            }
        }

    }

    override fun onErrorGetPatient(msg: String?) {
        toast(msg ?: "Gagal Mengambil Data Pasien").show()
    }

}