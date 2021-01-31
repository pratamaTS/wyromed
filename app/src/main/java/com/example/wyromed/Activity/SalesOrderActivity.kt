package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.SalesOrderPurchasedItemAdapter
import com.example.wyromed.Adapter.SalesOrderRentItemAdapter
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SalesOrderActivity : BaseActivity(), View.OnClickListener {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val ID = "id"
        val RENTAL = "rental"
        val BMHP = "bmhp"
    }

    var back: ImageButton? = null
    var btnSendSO: Button? = null
    var btnFinishSO: Button? = null
    var rvRentItem: RecyclerView? = null
    var rvPurchasedItem: RecyclerView? = null
    var salesOrderRentItemAdapter: SalesOrderRentItemAdapter? = null
    var salesOrderPurchasedItemAdapter: SalesOrderPurchasedItemAdapter? = null
    var salesRentItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var salesPurchasedItemList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var salesOrderHeader: ArrayList<HandoverRentalItem> = ArrayList()
    var salesOrderDetails: ArrayList<HandoverPurchasedItem> = ArrayList()
    var tvSelectPaymentMethod: TextView? = null
    var tvSelectedDueDate: TextView? = null
    var btnDueDate: ImageButton? = null
    var id: Int = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var calendar: Calendar? = null
    var year: Int? = null
    var month: Int? = null
    var day: Int? = null
    var dueDate: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales_order)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        btnSendSO = findViewById(R.id.btn_send_so)
        btnFinishSO = findViewById(R.id.btn_finish_so)
        rvRentItem = findViewById(R.id.rv_sales_order_rental_item)
        rvPurchasedItem = findViewById(R.id.rv_sales_order_puchased_item)
        tvSelectPaymentMethod = findViewById(R.id.tv_sales_order_payment_method)
        tvSelectedDueDate = findViewById(R.id.tv_sales_order_due_date)
        btnDueDate = findViewById(R.id.btn_open_calendar_due_date)

        id = intent.getIntExtra("id", 0)
        minutesOperation = intent.getLongExtra("minutes_ops",0)
        secondsOperation = intent.getLongExtra("seconds_ops",0)
        salesRentItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        salesPurchasedItemList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        //Set up Adapter Rent Item
        salesOrderRentItemAdapter = SalesOrderRentItemAdapter(this, salesRentItemList)
        rvRentItem?.setLayoutManager(LinearLayoutManager(this))
        rvRentItem?.setAdapter(salesOrderRentItemAdapter)
        rvRentItem?.setHasFixedSize(false)

        //Set up Adapter Purchase Item
        salesOrderPurchasedItemAdapter =
            SalesOrderPurchasedItemAdapter(this, salesPurchasedItemList)
        rvPurchasedItem?.setLayoutManager(LinearLayoutManager(this))
        rvPurchasedItem?.setAdapter(salesOrderPurchasedItemAdapter)
        rvPurchasedItem?.setHasFixedSize(false)
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
                    findViewById<View>(R.id.bottom_sheet_container_payment_method) as LinearLayout
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
                        Toast.makeText(
                            this@SalesOrderActivity,
                            "Payment Method",
                            Toast.LENGTH_SHORT
                        )
                            .show()
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
                }
            }
            R.id.btn_finish_so -> {
                val finishSO = Intent(this@SalesOrderActivity, AcceptSignatureActivity::class.java)
                startActivity(finishSO)
            }
        }
    }
}