package com.example.wyromed.Activity

import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.OrderedInterface
import com.example.wyromed.Activity.Presenter.OrderedPresenter
import com.example.wyromed.Adapter.StockAdapter
import com.example.wyromed.R
import com.example.wyromed.Response.Order.DataOrder
import com.example.wyromedapp.Adapter.ListOrderedItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class OrderedActivity: BaseActivity(), OrderedInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }

    var rvBookingOrdered: RecyclerView? = null
    var back: ImageButton? = null
    var toolbar: Toolbar? = null
    var tvStock: TextView? = null
    var adapter: StockAdapter? = null
    var tokenType: String? = null
    var token: String? = null
    var message: String?  = null
    var bookedItem: ArrayList<DataOrder> = ArrayList()
    var bookedAdapter: ListOrderedItemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ordered)

        //INIT VIEW
        rvBookingOrdered = findViewById(R.id.rv_ordered_booking)
        back = findViewById(R.id.ic_back)
        message = intent.getStringExtra("message")

        if(message != null){
            toast(message.toString()).show()
        }

        // init Button
        initActionButton()

        // Get Booking Ordered
        getBookingOrdered()
    }

    private fun initActionButton() {
        back!!.onClick {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    private fun getBookingOrdered() {
        tokenType = intent.getStringExtra("token_type")
        token = intent?.getStringExtra("token")

        if(tokenType==null || token==null){
            toast("Gagal mengambil data")?.show()
            finish()
        } else {
            OrderedPresenter(this).getAllBookingOrdered(tokenType, token)
        }
    }

    override fun onSuccessOrdered(dataOrdered: ArrayList<DataOrder?>?) {
        //Set Value
        bookedItem = dataOrdered as ArrayList<DataOrder>

        bookedAdapter = ListOrderedItemAdapter(this, bookedItem)

        rvBookingOrdered?.setLayoutManager(LinearLayoutManager(this))
        rvBookingOrdered?.setAdapter(bookedAdapter)
        rvBookingOrdered?.setItemAnimator(DefaultItemAnimator())
        rvBookingOrdered?.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onErrorOrdered(msg: String?) {
        toast("Failed to get data booking ordered")
    }
}