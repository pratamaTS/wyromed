package com.example.wyromed.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.StockInterface
import com.example.wyromed.Activity.Interface.TotalStockInterface
import com.example.wyromed.Activity.Presenter.StockItemPresenter
import com.example.wyromed.Activity.Presenter.TotalStockItemPresenter
import com.example.wyromed.Adapter.StockAdapter
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.StockRequestItem
import com.example.wyromed.R
import com.example.wyromed.Response.Stock.DataStock
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class StockActivity: BaseActivity(), StockInterface, TotalStockInterface, StockAdapter.CallbackStockInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var rvStock: RecyclerView? = null
    var back: ImageButton? = null
    var btnSubmit: Button? = null
    var stockRequest: ImageButton? = null
    var toolbar: Toolbar? = null
    var tvStock: TextView? = null
    var stockList: ArrayList<DataStock> = ArrayList()
    var adapter: StockAdapter? = null
    var tokenType: String? = null
    var token: String? = null
    var message: String?  = null
    var stockRequestItem: ArrayList<StockRequestDetails?>? = ArrayList()
    var stockRequestItemMin: ArrayList<StockRequestDetails?>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock)

        //INIT VIEW
        rvStock = findViewById(R.id.rv_stock)
        back = findViewById(R.id.ic_back)
        stockRequest = findViewById(R.id.ic_stock_request)
        tvStock = findViewById(R.id.tv_jumlah_stock)
        btnSubmit = findViewById(R.id.btn_submit)

        if(message != null){
            toast(message.toString()).show()
        }

        // init Button
        initActionButton()

        // Get Stock Item
        getAllStockItem()
    }

    fun getAllStockItem() {
        tokenType = intent.getStringExtra("token_type")
        token = intent.getStringExtra("token")

        if(tokenType==null || token==null){
            toast("Gagal mengambil data")?.show()
            finish()
        } else {
            TotalStockItemPresenter(this).getTotalStockItem(tokenType, token)
            StockItemPresenter(this).getAllStockItem(tokenType, token)
        }
    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
        }

        stockRequest!!.onClick {
            startActivity<HistoryReqStockActivity>(
                HistoryReqStockActivity.TAGS.TOKENTYPE to tokenType,
                HistoryReqStockActivity.TAGS.TOKEN to token,
                HistoryReqStockActivity.TAGS.MESSAGE to message,
            )
        }

        btnSubmit!!.onClick {
            startActivity<StockRequestActivity>(
                StockRequestActivity.TAGS.TOKENTYPE to tokenType,
                StockRequestActivity.TAGS.TOKEN to token,
                StockRequestActivity.TAGS.MESSAGE to message,
                StockRequestActivity.TAGS.PLUS to stockRequestItem,
                StockRequestActivity.TAGS.MINUS to stockRequestItemMin
            )
        }
    }

    override fun passDataCallback(stockAdd: ArrayList<StockRequestDetails?>?, stockMin: ArrayList<StockRequestDetails?>?) {
        stockRequestItem = stockAdd
        stockRequestItemMin = stockMin
    }

    override fun onSuccessGetStockItem(dataStockItem: ArrayList<DataStock?>?) {
        //Set Value
        stockList = dataStockItem as ArrayList<DataStock>

        //Set up Adapter
        adapter = StockAdapter(this,this, stockList)
        rvStock!!.setLayoutManager(LinearLayoutManager(this))
        rvStock!!.setAdapter(adapter)
        rvStock!!.setHasFixedSize(false)
    }

    override fun onErrorGetStockItem(msg: String?) {
        toast(msg ?: "Failed to get stock item").show()
    }

    override fun onSuccessGetTotalStockItem(dataTotalStockItem: Int?) {
        tvStock!!.text = dataTotalStockItem.toString()
    }

    override fun onErrorGetTotalStockItem(msg: String?) {
        toast(msg ?: "Failed to get total stock item").show()
    }
}