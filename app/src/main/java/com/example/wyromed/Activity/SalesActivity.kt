package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.GetSalesOrderInterface
import com.example.wyromed.Activity.Presenter.GetSalesOrderPresenter
import com.example.wyromed.Adapter.SalesAdapter
import com.example.wyromed.R
import com.example.wyromed.Response.SalesOrder.GetAllSO.DataGetSalesOrder
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast

class SalesActivity: BaseActivity(), GetSalesOrderInterface {
    var back: ImageButton? = null
    var rvSales: RecyclerView? = null
    var salesAdapter: SalesAdapter? = null
    var salesList: ArrayList<DataGetSalesOrder>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvSales = findViewById(R.id.rv_sales)

        loadSalesOrder()

        initActionButton()
    }

    private fun initActionButton() {
        back!!.onClick { finish() }
    }

    private fun loadSalesOrder(){
        GetSalesOrderPresenter(this).getAllSO(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                val intent = Intent(this@SalesActivity, SalesActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSuccessGetSalesOrder(dataSalesOrder: ArrayList<DataGetSalesOrder?>?) {
        //Set up Adapter
        salesAdapter = SalesAdapter(this, dataSalesOrder)
        rvSales?.setLayoutManager(LinearLayoutManager(this))
        rvSales?.setAdapter(salesAdapter)
        rvSales?.setHasFixedSize(false)
    }

    override fun onErrorGetSalesOrder(msg: String?) {
        toast(msg.toString()).show()
    }
}