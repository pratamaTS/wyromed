package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.example.wyromed.Adapter.OperationPurchasedAdapter
import com.example.wyromed.Adapter.OperationRentalAdapter
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.SalesOrderDetail
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromedapp.Adapter.ListRentalItemAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

class OperationActivity : AppCompatActivity(), View.OnClickListener, OperationRentalAdapter.RentalChronoTickListener {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val SOHEADER = "so_header"
        val RENTAL = "rental"
    }

    private lateinit var rvOrderRental: RecyclerView
    private lateinit var rvOrderPurchased: RecyclerView
    private lateinit var back: ImageButton
    private lateinit var btnFinish: Button
    private lateinit var btnAddItemPurchased: Button
    private lateinit var operationRentalAdapter: OperationRentalAdapter
    private lateinit var operationPurchasedAdapter: OperationPurchasedAdapter
    private lateinit var operationItemRental: ArrayList<HandoverRentalItem>
    private lateinit var operationItemPurchased: ArrayList<HandoverPurchasedItem>
    var spnItem: SmartMaterialSpinner<String>? = null
    var etAmount: EditText? = null
    var btnAddPurchasedItem: Button? = null
    var bottomSheetView: View? = null
    var bottomSheetDialog: BottomSheetDialog? = null
    private var productID: Int = 0
    private var itemName: String? = null
    private var productUnit: String? = null
    private var productEntity: String? = null
    private var totalQuantity: Int = 0
    var salesOrderHeader: SalesOrderHeader? = null
    var btnStopChronoRental: Chronometer? = null
    var hourOperation: Long = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var elapsedTimeOperation: Long = 0
    var alreadyStart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_operation)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_operation)
        rvOrderPurchased = findViewById(R.id.rv_order_purchased_operation)
        btnFinish = findViewById(R.id.btn_finish_operation)
        btnAddItemPurchased = findViewById(R.id.btn_add_item_purchased)

        salesOrderHeader = intent.getParcelableExtra("so_header")
        operationItemRental = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>

        bottomSheetDialog =
            BottomSheetDialog(this, R.style.BottomSheetDialogTheme)
        bottomSheetView = LayoutInflater.from(applicationContext).inflate(
            R.layout.layout_bottom_sheet_item_purchased,
            findViewById<View>(R.id.bottom_sheet_container_purchased) as LinearLayout
        )

        spnItem = findViewById(R.id.purchased_item_id)
        etAmount = findViewById(R.id.edt_amount_pitem)
        btnAddPurchasedItem = findViewById(R.id.btn_add_purchased)

        //SET LISTENER
        back.setOnClickListener(this)
        btnFinish.setOnClickListener(this)
        btnAddItemPurchased.setOnClickListener(this)
        btnAddPurchasedItem?.setOnClickListener(this)

        //Setup adapter rental
        operationRentalAdapter = OperationRentalAdapter(this, this, operationItemRental)
        rvOrderRental.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental.setAdapter(operationRentalAdapter)
        rvOrderRental.setHasFixedSize(false)

    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.ic_back -> finish()
            R.id.btn_finish_operation -> {
                btnStopChronoRental?.stop()
                hourOperation = (SystemClock.elapsedRealtime() - btnStopChronoRental!!.base) / 3600000
                minutesOperation =
                    (SystemClock.elapsedRealtime() - btnStopChronoRental!!.base) / 1000 / 60
                secondsOperation =
                    (SystemClock.elapsedRealtime() - btnStopChronoRental!!.base) / 1000 % 60
                elapsedTimeOperation = SystemClock.elapsedRealtime()

                Log.d("hoursChrono", hourOperation.toString())
                Log.d("minuteChrono", minutesOperation.toString())
                Log.d("secondChrono", secondsOperation.toString())
                Log.d("elapsedMillis", elapsedTimeOperation.toString())

                startActivity<FinishOperationActivity>(
                    FinishOperationActivity.TAGS.HOURSOPS to hourOperation,
                    FinishOperationActivity.TAGS.MINUTESOPS to minutesOperation,
                    FinishOperationActivity.TAGS.SECONDSOPS to secondsOperation,
                    FinishOperationActivity.TAGS.RENTAL to operationItemRental,
                    FinishOperationActivity.TAGS.BMHP to operationItemPurchased,
                    FinishOperationActivity.TAGS.SOHEADER to salesOrderHeader
                )

                finish()
            }
            R.id.btn_add_item_purchased -> {
                bottomSheetDialog?.show()
            }
            R.id.btn_add_purchased -> {
                if (etAmount?.text.isNullOrEmpty()) {
                    toast("Amount Item cannot be empty").show()
                } else {
                    // Rental Item

                    val checkBookingDetails = operationItemPurchased.find {
                        it.productId == productID
                    }

                    if(operationItemPurchased.isNotEmpty()){
                        if(checkBookingDetails != null) {
                            operationItemPurchased.find {
                                it.productId == productID
                            }?.quantity = etAmount?.text.toString().toInt()
                        }else{
                            operationItemPurchased.add(
                                HandoverPurchasedItem(
                                    productID,
                                    etAmount?.text.toString().toInt(),
                                    itemName.toString(),
                                    productUnit.toString(),
                                    productEntity.toString()
                                )
                            )
                        }
                    }else{
                        operationItemPurchased.add(
                            HandoverPurchasedItem(
                                productID,
                                etAmount?.text.toString().toInt(),
                                itemName.toString(),
                                productUnit.toString(),
                                productEntity.toString()
                            )
                        )
                    }

                    totalQuantity = totalQuantity + etAmount?.text.toString().toInt()

                    //Setup adapter purchased
                    operationPurchasedAdapter = OperationPurchasedAdapter(this, operationItemPurchased)
                    rvOrderPurchased.setLayoutManager(LinearLayoutManager(this))
                    rvOrderPurchased.setAdapter(operationPurchasedAdapter)
                    rvOrderPurchased.setHasFixedSize(false)

                    bottomSheetDialog!!.dismiss()
                    etAmount!!.text.clear()
                }
            }
        }
    }

    override fun onRentalChronoTickListener(
        chronoRental: Chronometer,
        hours: Long,
        minutes: Long,
        second: Long,
        elapsedTime: Long
    ) {
        btnStopChronoRental = chronoRental
        hourOperation = (SystemClock.elapsedRealtime() / 3600000)
        minutesOperation = (elapsedTime - chronoRental.getBase()) / 1000 / 60
        secondsOperation = (elapsedTime - chronoRental.getBase()) / 1000 % 60
        elapsedTimeOperation = elapsedTime + 1000
    }
}