package com.example.wyromed.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.InUsePurchasedAdapter
import com.example.wyromed.Adapter.InUseRentalAdapter
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import java.util.*
import kotlin.collections.ArrayList

class InUseActivity : BaseActivity(), InUseRentalAdapter.RentalChronoTickListener, InUsePurchasedAdapter.PurchasedChronoTickListener {
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
    var btnFinish: Button? = null
    var inUseRentalAdapter: InUseRentalAdapter? = null
    var inUsePurchasedAdapter: InUsePurchasedAdapter? = null
    var inUseItemRental: ArrayList<HandoverRentalItem> = ArrayList()
    var inUseItemPurchased: ArrayList<HandoverPurchasedItem> = ArrayList()
    var id: Int = 0
    var message: String = ""
    var btnStopChronoRental: Chronometer? = null
    var btnStopChronoPurchased: Chronometer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_use)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_operation)
        rvOrderPurchased = findViewById(R.id.rv_order_purchased_operation)
        btnFinish = findViewById(R.id.btn_finish_operation)

        user?.token_type = intent.getStringExtra("token_type")
        user?.token = intent.getStringExtra("token")
        id = intent.getIntExtra("id", 0)
        inUseItemRental = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        inUseItemPurchased = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        //Setup adapter rental
        inUseRentalAdapter = InUseRentalAdapter(this, this, inUseItemRental)
        rvOrderRental?.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental?.setAdapter(inUseRentalAdapter)
        rvOrderRental?.setHasFixedSize(false)

        //Setup adapter purchased
        inUsePurchasedAdapter = InUsePurchasedAdapter(this, this, inUseItemPurchased)
        rvOrderPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvOrderPurchased?.setAdapter(inUsePurchasedAdapter)
        rvOrderPurchased?.setHasFixedSize(false)

        initActionButton()
    }

    private fun initActionButton(){
        back?.onClick { finish() }
        btnFinish?.onClick {

            btnStopChronoRental?.stop()
            btnStopChronoPurchased?.stop()

            startActivity<FinishOperationActivity>(
                FinishOperationActivity.TAGS.TOKENTYPE to user?.token_type,
                FinishOperationActivity.TAGS.TOKEN to user?.token,
                FinishOperationActivity.TAGS.MESSAGE to message,
                FinishOperationActivity.TAGS.ID to id,
                FinishOperationActivity.TAGS.RENTAL to inUseItemRental,
                FinishOperationActivity.TAGS.BMHP to inUseItemPurchased)
        }
    }

    override fun onRentalChronoTickListener(chronoRental: Chronometer) {
        btnStopChronoRental = chronoRental
    }

    override fun onPurchasedChronoTickListener(chronoPurchased: Chronometer) {
        btnStopChronoPurchased = chronoPurchased
    }
}