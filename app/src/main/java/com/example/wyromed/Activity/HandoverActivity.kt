package com.example.wyromed.Activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chinodev.androidneomorphframelayout.NeomorphFrameLayout
import com.example.wyromed.Activity.Interface.HandoverDetailBookingInterface
import com.example.wyromed.Activity.Presenter.DetailHeaderBookingPresenter
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.Response.Handover.DataHandoverDetail
import com.example.wyromedapp.Adapter.ListHandoverPItemAdapter
import com.example.wyromedapp.Adapter.ListHandoverRItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HandoverActivity : BaseActivity(), HandoverDetailBookingInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val CONFIRM = "confirm"
        val ID = "id"
    }
    var rvOrderRental: RecyclerView? = null
    var rvOrderPurchased:RecyclerView? = null
    var back: ImageButton? = null
    var btnAccept: Button? = null
    var btnStart: Button? = null
    var rentalItemAdapter: ListHandoverRItemAdapter? = null
    var purchasedItemAdapter: ListHandoverPItemAdapter? = null
    var detailHandoverBooking: ArrayList<DataHandoverDetail> = ArrayList()
    var orderRentalItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var orderPurchasedItemList: ArrayList<HandoverPurchasedItem> = ArrayList()
    var layoutBtnAccept: NeomorphFrameLayout? = null
    var layoutBtnStart:NeomorphFrameLayout? = null
    var id: Int = 0
    var message: String = ""
    var confirm: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handover)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_item)
        rvOrderPurchased = findViewById(R.id.rv_order_purchased_item)
        btnAccept = findViewById(R.id.btn_accept_order)
        btnStart = findViewById(R.id.btn_starst_operation)
        layoutBtnAccept = findViewById(R.id.layout_btn_accept)
        layoutBtnStart = findViewById(R.id.layout_btn_start)

        id = intent.getIntExtra("id", 0)
        confirm = intent.getBooleanExtra("confirm", false)

        //Visibity Button
        if(confirm == false) {
            layoutBtnAccept?.setVisibility(View.VISIBLE)
            layoutBtnStart?.setVisibility(View.INVISIBLE)
        }else{
            layoutBtnAccept?.setVisibility(View.INVISIBLE)
            layoutBtnStart?.setVisibility(View.VISIBLE)
        }

        //SET LISTENER
        initActionButton()

        getHandover()
    }

    private fun getHandover(){
        DetailHeaderBookingPresenter(this).getDetailHandoverBooking(
            this,
            id
        )
    }

    fun onClick(v: View) {
        when (v.id) {
            R.id.ic_back -> finish()
        }
    }

    private fun initActionButton() {
        back!!.onClick { this }
        btnAccept!!.onClick {
            startActivity<ConfirmSignatureActivity>(
                ConfirmSignatureActivity.TAGS.ID to id,
                ConfirmSignatureActivity.TAGS.CONFIRM to confirm,
            )

            finish()
        }

        btnStart!!.onClick{
            startActivity<InUseActivity>(
                InUseActivity.TAGS.MESSAGE to message,
                InUseActivity.TAGS.ID to id,
                InUseActivity.TAGS.RENTAL to orderRentalItemList,
                InUseActivity.TAGS.BMHP to orderPurchasedItemList
            )
            finish()
        }
    }

    override fun onSuccessHandoverDetailBooking(
        message: String?,
        dataDetailHandOver: ArrayList<DataHandoverDetail?>?
    ) {
        detailHandoverBooking = dataDetailHandOver as ArrayList<DataHandoverDetail>

        for(itemDetail in detailHandoverBooking){
            if(itemDetail.productEntity == "TINDAKAN"){
                orderRentalItemList.add(
                    HandoverRentalItem(
                        itemDetail.productId!!.toInt(),
                        itemDetail.quantity!!.toInt(),
                        itemDetail.productName,
                        itemDetail.productUnit,
                        itemDetail.productEntity
                    )
                )

                //Set up adapter Rental
                rentalItemAdapter = ListHandoverRItemAdapter(this, orderRentalItemList, confirm)
                rvOrderRental!!.setLayoutManager(LinearLayoutManager(this))
                rvOrderRental!!.setAdapter(rentalItemAdapter)
                rvOrderRental!!.setHasFixedSize(false)
            }else{
                orderPurchasedItemList.add(
                    HandoverPurchasedItem(
                        itemDetail.productId!!.toInt(),
                        itemDetail.quantity!!.toInt(),
                        itemDetail.productName,
                        itemDetail.productUnit,
                        itemDetail.productEntity
                    )
                )

                //Set up adapter Purchased
                purchasedItemAdapter = ListHandoverPItemAdapter(this, orderPurchasedItemList, confirm)
                rvOrderPurchased!!.setLayoutManager(LinearLayoutManager(this))
                rvOrderPurchased!!.setAdapter(purchasedItemAdapter)
                rvOrderPurchased!!.setHasFixedSize(false)
            }
        }
    }

    override fun onErrorHandoverDetailBooking(msg: String?) {
        toast("Failed to get data handover")
    }
}