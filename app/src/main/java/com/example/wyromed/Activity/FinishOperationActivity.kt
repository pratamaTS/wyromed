package com.example.wyromed.Activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.deishelon.roundedbottomsheet.RoundedBottomSheetDialog
import com.example.wyromed.Activity.Interface.PurchasedItemInterface
import com.example.wyromed.Activity.Presenter.PurchasedItemPresenter
import com.example.wyromed.Adapter.FinishPurchasedAdapter
import com.example.wyromed.Adapter.FinishRentalAdapter
import com.example.wyromed.Adapter.OperationPurchasedAdapter
import com.example.wyromed.Data.Model.SalesOrderDetail
import com.example.wyromed.Data.Model.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.Model.Header.ListPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.Response.PurchasedItem.DataPurchasedItem
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class FinishOperationActivity: BaseActivity(), FinishPurchasedAdapter.CallbackStockFinishInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val HOURSOPS = "hours_ops"
        val MINUTESOPS = "minutes_ops"
        val SECONDSOPS = "seconds_ops"
        val RENTAL = "rental"
        val BMHP = "bmhp"
        val LIST_BMHP = "list_bmhp"
        val SOHEADER = "so_header"
    }

    var rvOrderRental: RecyclerView? = null
    var rvOrderPurchased: RecyclerView? = null
    var back: ImageButton? = null
    var btnSubmit: Button? = null
    var finishRentalAdapter: FinishRentalAdapter? = null
    var finishPurchasedAdapter: FinishPurchasedAdapter? = null
    var finishRentalItemList: ArrayList<HandoverRentalItem> = ArrayList()
    var finishPurchaseItemList: ArrayList<HandoverPurchasedItem> = ArrayList()

    var salesOrderHeader: SalesOrderHeader = SalesOrderHeader()
    var hoursOperation: Long = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var message: String = ""
    var spnItem: SmartMaterialSpinner<String>? = null
    var etAmount: EditText? = null
    var btnAddPurchasedItem: Button? = null
    private lateinit var btnAddItemPurchased: Button
    var bottomSheetView: View? = null
    var bottomSheetDialog: RoundedBottomSheetDialog? = null
    private var productID: Int = 0
    private var itemName: String? = null
    private var productUnit: String? = null
    private var productEntity: String? = null
    private var totalQuantity: Int = 0
    var productId: Int? = null
    var listItemPurchased: ArrayList<ListPurchasedItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_operation)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_finish)
        rvOrderPurchased = findViewById(R.id.rv_order_purchased_finish)
        btnSubmit = findViewById(R.id.btn_submit_sales)
        btnAddItemPurchased = findViewById(R.id.btn_add_item_purchased_finish)

        hoursOperation = intent.getLongExtra("hours_ops",0)
        minutesOperation = intent.getLongExtra("minutes_ops",0)
        secondsOperation = intent.getLongExtra("seconds_ops",0)
        salesOrderHeader = intent.getParcelableExtra("so_header")!!

        finishRentalItemList = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        finishPurchaseItemList = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>
        listItemPurchased = intent.getParcelableArrayListExtra<ListPurchasedItem>("list_bmhp") as ArrayList<ListPurchasedItem>

        val duration = SimpleDateFormat("H:m:ss").parse(hoursOperation.toString() + ":" + minutesOperation.toString() + ":" + secondsOperation.toString())
        val newFormatDuration = SimpleDateFormat("HH:mm:ss").format(duration)

        salesOrderHeader.duration = newFormatDuration

        //Setup adapter rental
        finishRentalAdapter = FinishRentalAdapter(this, finishRentalItemList, hoursOperation, minutesOperation, secondsOperation)
        rvOrderRental?.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental?.setAdapter(finishRentalAdapter)
        rvOrderRental?.setHasFixedSize(false)

        //Setup adapter purchased
        showPurchasedItem()

        bottomSheetView = layoutInflater.inflate(R.layout.bottom_sheet_purchased_item, null)

        spnItem = bottomSheetView?.findViewById(R.id.purchased_item_id)
        etAmount = bottomSheetView?.findViewById(R.id.edt_amount_pitem)
        btnAddPurchasedItem = bottomSheetView?.findViewById(R.id.btn_add_purchased)

        bottomSheetDialog = RoundedBottomSheetDialog(this)
        bottomSheetDialog?.setContentView(bottomSheetView!!)

        setSpinner()

        initActionButton()
    }

    private fun setSpinner(){
        //Set Value
        val itemList: ArrayList<String> = ArrayList()
        if (listItemPurchased != null) {
            for( i in listItemPurchased ){
                itemList.add(i?.name.toString())
            }
        }

        // Spinner Purchased Item
        spnItem?.setItem(itemList)
        spnItem?.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                adapterView: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                val item = listItemPurchased?.get(position)
                productId = item!!.productId
                itemName = item.name
                productUnit = item.unitName
                productEntity = item.entity
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                toast("There is no item").show()
            }
        })
    }

    private fun showPurchasedItem(){
        //Setup adapter purchased
        finishPurchasedAdapter = FinishPurchasedAdapter(this, this, finishPurchaseItemList)
        rvOrderPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvOrderPurchased?.setAdapter(finishPurchasedAdapter)
        rvOrderPurchased?.setHasFixedSize(false)
    }

    private fun initActionButton(){
        back!!.onClick { finish() }
        btnSubmit!!.onClick {

            startActivity<ReceiptActivity>(
                ReceiptActivity.TAGS.MESSAGE to message,
                ReceiptActivity.TAGS.HOURSOPS to hoursOperation,
                ReceiptActivity.TAGS.MINUTESOPS to minutesOperation,
                ReceiptActivity.TAGS.SECONDSOPS to secondsOperation,
                ReceiptActivity.TAGS.RENTAL to finishRentalItemList,
                ReceiptActivity.TAGS.BMHP to finishPurchaseItemList,
                ReceiptActivity.TAGS.SOHEADER to salesOrderHeader
            )
            finish()
        }
        btnAddItemPurchased.onClick {
            bottomSheetDialog?.show()
        }
        btnAddPurchasedItem?.onClick {
            if (etAmount?.text.isNullOrEmpty()) {
                toast("Amount Item cannot be empty").show()
            } else {
                // Rental Item

                val checkBookingDetails = finishPurchaseItemList.find {
                    it.productId == productID
                }

                if(finishPurchaseItemList.isNotEmpty()){
                    if(checkBookingDetails != null) {
                        finishPurchaseItemList.find {
                            it.productId == productID
                        }?.quantity = etAmount?.text.toString().toInt()
                    }else{
                        finishPurchaseItemList.add(
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
                    finishPurchaseItemList.add(
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

                showPurchasedItem()
                bottomSheetDialog?.dismiss()
                etAmount?.text?.clear()
            }
        }
    }

    override fun passDataFinishCallback(stockChange: ArrayList<HandoverPurchasedItem>) {
        finishPurchaseItemList = stockChange
    }
}