package com.example.wyromed.Activity

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.InUsePurchasedAdapter
import com.example.wyromed.Adapter.InUseRentalAdapter
import com.example.wyromed.Model.Body.SalesOrderHeader
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity


class InUseActivity : BaseActivity(), InUseRentalAdapter.RentalChronoTickListener {
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
    var hourOperation: Long = 0
    var minutesOperation: Long = 0
    var secondsOperation: Long = 0
    var elapsedTimeOperation: Long = 0
    var alreadyStart: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_in_use)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvOrderRental = findViewById(R.id.rv_order_rental_operation)
        rvOrderPurchased = findViewById(R.id.rv_order_purchased_operation)
        btnFinish = findViewById(R.id.btn_finish_operation)

        id = intent.getIntExtra("id", 0)
        inUseItemRental = intent.getParcelableArrayListExtra<HandoverRentalItem>("rental") as ArrayList<HandoverRentalItem>
        inUseItemPurchased = intent.getParcelableArrayListExtra<HandoverPurchasedItem>("bmhp") as ArrayList<HandoverPurchasedItem>

        //Setup adapter rental
        inUseRentalAdapter = InUseRentalAdapter(this, this, inUseItemRental, alreadyStart)
        rvOrderRental?.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental?.setAdapter(inUseRentalAdapter)
        rvOrderRental?.setHasFixedSize(false)

        //Setup adapter purchased
        inUsePurchasedAdapter = InUsePurchasedAdapter(this, inUseItemPurchased)
        rvOrderPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvOrderPurchased?.setAdapter(inUsePurchasedAdapter)
        rvOrderPurchased?.setHasFixedSize(false)

        initActionButton()
    }

    override fun onResume() {
        super.onResume()

        alreadyStart = true

        //Setup adapter rental
        inUseRentalAdapter = InUseRentalAdapter(this, this, inUseItemRental, alreadyStart)
        rvOrderRental?.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental?.setAdapter(inUseRentalAdapter)
        rvOrderRental?.setHasFixedSize(false)

        //Setup adapter purchased
        inUsePurchasedAdapter = InUsePurchasedAdapter(this, inUseItemPurchased)
        rvOrderPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvOrderPurchased?.setAdapter(inUsePurchasedAdapter)
        rvOrderPurchased?.setHasFixedSize(false)
    }

    override fun onPause() {
        super.onPause()

        alreadyStart = true

        //Setup adapter rental
        inUseRentalAdapter = InUseRentalAdapter(this, this, inUseItemRental, alreadyStart)
        rvOrderRental?.setLayoutManager(LinearLayoutManager(this))
        rvOrderRental?.setAdapter(inUseRentalAdapter)
        rvOrderRental?.setHasFixedSize(false)

        //Setup adapter purchased
        inUsePurchasedAdapter = InUsePurchasedAdapter(this, inUseItemPurchased)
        rvOrderPurchased?.setLayoutManager(LinearLayoutManager(this))
        rvOrderPurchased?.setAdapter(inUsePurchasedAdapter)
        rvOrderPurchased?.setHasFixedSize(false)
    }

    private fun initActionButton(){
        back?.onClick { finish() }
        btnFinish?.onClick {
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
                FinishOperationActivity.TAGS.MESSAGE to message,
                FinishOperationActivity.TAGS.ID to id,
                FinishOperationActivity.TAGS.HOURSOPS to hourOperation,
                FinishOperationActivity.TAGS.MINUTESOPS to minutesOperation,
                FinishOperationActivity.TAGS.SECONDSOPS to secondsOperation,
                FinishOperationActivity.TAGS.RENTAL to inUseItemRental,
                FinishOperationActivity.TAGS.BMHP to inUseItemPurchased
            )

            finish()
        }
    }

    override fun onRentalChronoTickListener(chronoRental: Chronometer, hours: Long, minutes: Long, second: Long, elapsedTime: Long) {
//        minutesOperation = minutes
//        secondsOperation = second
//        elapsedTimeOperation = elapsedTime
        btnStopChronoRental = chronoRental
        hourOperation = (SystemClock.elapsedRealtime() / 3600000)
        minutesOperation = (elapsedTime - chronoRental.getBase()) / 1000 / 60
        secondsOperation = (elapsedTime - chronoRental.getBase()) / 1000 % 60
        elapsedTimeOperation = elapsedTime + 1000
    }

}