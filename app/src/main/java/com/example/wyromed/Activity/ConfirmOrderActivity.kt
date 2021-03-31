package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.ConfirmRentalAdapter
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import org.jetbrains.anko.startActivity

class ConfirmOrderActivity: BaseActivity(), View.OnClickListener {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val ID = "id"
        val SOHEADER = "so_header"
        val BOOKING = "booking_rental_item"
    }

    private lateinit var rvOrderRental: RecyclerView
    private lateinit var rentalItemAdapter: ConfirmRentalAdapter
    private var orderRentalItemList: ArrayList<HandoverRentalItem> = ArrayList()

    private lateinit var back: ImageButton
    private lateinit var btnStart: Button
    var salesOrderHeader: SalesOrderHeader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_order)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_item)
        btnStart = findViewById(R.id.btn_start_operation)

        //SET LISTENER
        back.setOnClickListener(this)
        btnStart.setOnClickListener(this)

        salesOrderHeader = intent.getParcelableExtra("so_header")
        orderRentalItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("booking_rental_item") as ArrayList<HandoverRentalItem>

        //Set up adapter Rental
        rentalItemAdapter = ConfirmRentalAdapter(this, orderRentalItemList)
        rvOrderRental.layoutManager = LinearLayoutManager(this)
        rvOrderRental.adapter = rentalItemAdapter
        rvOrderRental.setHasFixedSize(false)
    }

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.ic_back -> finish()
            R.id.btn_start_operation -> {
                startActivity<OperationActivity>(
                    OperationActivity.TAGS.SOHEADER to salesOrderHeader,
                    OperationActivity.TAGS.RENTAL to orderRentalItemList
                )
                finish()
            }
        }
    }
}