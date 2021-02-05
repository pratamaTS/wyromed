package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.FinishPurchasedAdapter
import com.example.wyromed.Adapter.FinishRentalAdapter
import com.example.wyromed.Adapter.StockAdapter
import com.example.wyromed.Model.Body.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class FinishOperationActivity: BaseActivity(), FinishPurchasedAdapter.CallbackStockFinishInterface {
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

    var rvOrderRental: RecyclerView? = null
    var rvOrderPurchased: RecyclerView? = null
    var back: ImageButton? = null
    var btnSubmit: Button? = null
    var finishRentalAdapter: FinishRentalAdapter? = null
    var finishPurchasedAdapter: FinishPurchasedAdapter? = null
    var finishRentalItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var finishPurchaseItemList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var id: Int = 0
    var hoursOperation: Long = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_operation)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_finish)
        rvOrderPurchased = findViewById(R.id.rv_order_purchased_finish)
        btnSubmit = findViewById(R.id.btn_submit_sales)

        id = intent.getIntExtra("id", 0)
        hoursOperation = intent.getLongExtra("hours_ops",0)
        minutesOperation = intent.getLongExtra("minutes_ops",0)
        secondsOperation = intent.getLongExtra("seconds_ops",0)

        finishRentalItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        finishPurchaseItemList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        //Setup adapter rental
        finishRentalAdapter = FinishRentalAdapter(this, finishRentalItemList, hoursOperation, minutesOperation, secondsOperation)
        rvOrderRental?.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental?.setAdapter(finishRentalAdapter)
        rvOrderRental?.setHasFixedSize(false)

        //Setup adapter purchased
        finishPurchasedAdapter = FinishPurchasedAdapter(this, this, finishPurchaseItemList)
        rvOrderPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvOrderPurchased?.setAdapter(finishPurchasedAdapter)
        rvOrderPurchased?.setHasFixedSize(false)

        initActionButton()
    }

    private fun initActionButton(){
        back!!.onClick { finish() }
        btnSubmit!!.onClick {
            startActivity<ReceiptActivity>(
                ReceiptActivity.TAGS.MESSAGE to message,
                ReceiptActivity.TAGS.ID to id,
                ReceiptActivity.TAGS.HOURSOPS to hoursOperation,
                ReceiptActivity.TAGS.MINUTESOPS to minutesOperation,
                ReceiptActivity.TAGS.SECONDSOPS to secondsOperation,
                ReceiptActivity.TAGS.RENTAL to finishRentalItemList,
                ReceiptActivity.TAGS.BMHP to finishPurchaseItemList
            )
            finish()
        }
    }

    override fun passDataFinishCallback(stockChange: ArrayList<HandoverPurchasedItem>) {
        finishPurchaseItemList = stockChange
    }
}