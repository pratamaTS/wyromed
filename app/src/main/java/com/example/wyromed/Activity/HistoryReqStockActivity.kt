package com.example.wyromed.Activity

import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.wyromed.Activity.Interface.StockRequestInterface
import com.example.wyromed.Activity.Presenter.StockRequestPresenter
import com.example.wyromed.Adapter.StockAdapter
import com.example.wyromed.R
import com.example.wyromed.Response.StockRequest.DataGetStockRequest
import com.example.wyromedapp.Adapter.ListStockRequestItemAdapter
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.toast

class HistoryReqStockActivity: BaseActivity(), StockRequestInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }

    var rvReqStock: RecyclerView? = null
    var back: ImageButton? = null
    var toolbar: Toolbar? = null
    var adapter: StockAdapter? = null
    var message: String?  = null
    var reqStockItem: ArrayList<DataGetStockRequest> = ArrayList()
    var reqStockAdapter: ListStockRequestItemAdapter? = null
    var swipeRefresh: SwipeRefreshLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_stock)

        //INIT VIEW
        rvReqStock = findViewById(R.id.rv_stock_request)
        swipeRefresh = findViewById(R.id.refresh)
        back = findViewById(R.id.ic_back)
        message = intent.getStringExtra("message")

        if(message != null){
            toast(message.toString()).show()
        }

        swipeRefresh!!.setOnRefreshListener(OnRefreshListener {
            StockRequestPresenter(this).getAllStockRequest(this)
            swipeRefresh!!.setRefreshing(false)
        })

        // init Button
        initActionButton()

        // Get Request Stock
        getRequestStock()
    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
        }
    }

    private fun getRequestStock() {
        StockRequestPresenter(this).getAllStockRequest(this)
    }

    override fun onSuccessGetStockRequest(
        message: String?,
        dataReqStock: ArrayList<DataGetStockRequest?>?
    ) {
        //Set Value
        reqStockItem = dataReqStock as ArrayList<DataGetStockRequest>

        reqStockAdapter = ListStockRequestItemAdapter(this, reqStockItem)

        rvReqStock?.setLayoutManager(LinearLayoutManager(this))
        rvReqStock?.setAdapter(reqStockAdapter)
        rvReqStock?.setItemAnimator(DefaultItemAnimator())
        rvReqStock?.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onErrorGetStockRequest(msg: String?) {
        toast("Failed to get data stock request")
    }
}