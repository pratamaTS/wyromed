package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.FinishPurchasedAdapter
import com.example.wyromed.Adapter.FinishRentalAdapter
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.collections.ArrayList

class FinishOperationActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val ID = "id"
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
    var message: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_operation)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_finish)
        rvOrderPurchased = findViewById(R.id.rv_order_purchased_finish)
        btnSubmit = findViewById(R.id.btn_submit_sales)

        user?.token_type = intent.getStringExtra("token_type")
        user?.token = intent.getStringExtra("token")
        id = intent.getIntExtra("id", 0)
        finishRentalItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        finishPurchaseItemList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>


        //Setup adapter rental
        finishRentalAdapter = FinishRentalAdapter(this, finishRentalItemList)
        rvOrderRental?.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental?.setAdapter(finishRentalAdapter)
        rvOrderRental?.setHasFixedSize(false)

        //Setup adapter purchased
        finishPurchasedAdapter = FinishPurchasedAdapter(this, finishPurchaseItemList)
        rvOrderPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvOrderPurchased?.setAdapter(finishPurchasedAdapter)
        rvOrderPurchased?.setHasFixedSize(false)
    }

    @SuppressLint("NonConstantResourceId")
    fun onClick(v: View) {
        when (v.id) {
            R.id.ic_back -> finish()
            R.id.btn_submit_sales -> {
                val submitSales = Intent(this@FinishOperationActivity, ReceiptActivity::class.java)
                startActivity(submitSales)
            }
        }
    }

    private fun initActionButton(){
        back!!.onClick { finish() }
        btnSubmit!!.onClick {
            startActivity<ReceiptActivity>(
                ReceiptActivity.TAGS.TOKENTYPE to user?.token_type,
                ReceiptActivity.TAGS.TOKEN to user?.token,
                ReceiptActivity.TAGS.MESSAGE to message,
                ReceiptActivity.TAGS.ID to id,
                ReceiptActivity.TAGS.RENTAL to finishRentalItemList,
                ReceiptActivity.TAGS.BMHP to finishPurchaseItemList
            )
        }
    }
}