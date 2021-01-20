package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.ReceiptPurchasedAdapter
import com.example.wyromed.Adapter.ReceiptRentalAdapter
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import java.util.*
import kotlin.collections.ArrayList

class ReceiptActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val ID = "id"
        val RENTAL = "rental"
        val BMHP = "bmhp"
    }

    var rvReceiptRental: RecyclerView? = null
    var rvReceiptPurchased:RecyclerView? = null
    var back: ImageButton? = null
    var btnCreateSO: Button? = null
    var receiptRentalAdapter: ReceiptRentalAdapter? = null
    var receiptPurchasedAdapter: ReceiptPurchasedAdapter? = null
    var receiptRentalItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var receiptPurchasedList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var id: Int = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receipt)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvReceiptRental = findViewById(R.id.rv_order_rental_receipt)
        rvReceiptPurchased = findViewById(R.id.rv_order_purchased_receipt)
        btnCreateSO = findViewById(R.id.btn_create_so)

        user?.token_type = intent.getStringExtra("token_type")
        user?.token = intent.getStringExtra("token")
        id = intent.getIntExtra("id", 0)
        minutesOperation = intent.getLongExtra("minutes_ops",0)
        secondsOperation = intent.getLongExtra("seconds_ops",0)
        receiptRentalItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        receiptPurchasedList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        //Setup adapter rental
        receiptRentalAdapter = ReceiptRentalAdapter(this, receiptRentalItemList, minutesOperation, secondsOperation)
        rvReceiptRental?.setLayoutManager(LinearLayoutManager(this))
        rvReceiptRental?.setAdapter(receiptRentalAdapter)
        rvReceiptRental?.setHasFixedSize(false)

        //Setup adapter purchased
        receiptPurchasedAdapter = ReceiptPurchasedAdapter(this, receiptPurchasedList)
        rvReceiptPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvReceiptPurchased?.setAdapter(receiptPurchasedAdapter)
        rvReceiptPurchased?.setHasFixedSize(false)
    }
}