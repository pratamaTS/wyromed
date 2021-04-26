package com.example.wyromed.Activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.ReceiptPurchasedAdapter
import com.example.wyromed.Adapter.ReceiptRentalAdapter
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.collections.ArrayList

class ReceiptActivity: BaseActivity() {
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

    var rvReceiptRental: RecyclerView? = null
    var rvReceiptPurchased:RecyclerView? = null
    var back: ImageButton? = null
    var btnCreateSO: Button? = null
    var receiptRentalAdapter: ReceiptRentalAdapter? = null
    var receiptPurchasedAdapter: ReceiptPurchasedAdapter? = null
    var receiptRentalItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var receiptPurchasedList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var hoursOperation: Long = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var message: String? = null

    var salesOrderHeader: SalesOrderHeader = SalesOrderHeader()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvReceiptRental = findViewById(R.id.rv_order_rental_receipt)
        rvReceiptPurchased = findViewById(R.id.rv_order_purchased_receipt)
        btnCreateSO = findViewById(R.id.btn_create_so)

        hoursOperation = intent.getLongExtra("hours_ops",0)
        minutesOperation = intent.getLongExtra("minutes_ops",0)
        secondsOperation = intent.getLongExtra("seconds_ops",0)
        salesOrderHeader = intent.getParcelableExtra("so_header")!!

        if(intent.hasExtra("sales_order_header")) {
            salesOrderHeader = intent.getParcelableExtra<SalesOrderHeader>("sales_order_header")!!
        }
        receiptRentalItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        receiptPurchasedList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        //Setup adapter rental
        receiptRentalAdapter = ReceiptRentalAdapter(this, receiptRentalItemList, hoursOperation, minutesOperation, secondsOperation)
        rvReceiptRental?.setLayoutManager(LinearLayoutManager(this))
        rvReceiptRental?.setAdapter(receiptRentalAdapter)
        rvReceiptRental?.setHasFixedSize(false)

        //Setup adapter purchased
        receiptPurchasedAdapter = ReceiptPurchasedAdapter(this, receiptPurchasedList)
        rvReceiptPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvReceiptPurchased?.setAdapter(receiptPurchasedAdapter)
        rvReceiptPurchased?.setHasFixedSize(false)

        initActionButton()
    }

    private fun initActionButton(){
        btnCreateSO!!.onClick {
            startActivity<SalesOrderActivity>(
                SalesOrderActivity.TAGS.MESSAGE to message,
                SalesOrderActivity.TAGS.HOURSOPS to hoursOperation,
                SalesOrderActivity.TAGS.MINUTESOPS to minutesOperation,
                SalesOrderActivity.TAGS.SECONDSOPS to secondsOperation,
                SalesOrderActivity.TAGS.RENTAL to receiptRentalItemList,
                SalesOrderActivity.TAGS.BMHP to receiptPurchasedList,
                SalesOrderActivity.TAGS.SOHEADER to salesOrderHeader
            )
            finish()
        }
    }
}