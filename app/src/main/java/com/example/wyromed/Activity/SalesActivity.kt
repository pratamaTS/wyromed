package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.SalesAdapter
import com.example.wyromed.Model.Sales
import com.example.wyromed.R
import org.jetbrains.anko.sdk25.coroutines.onClick

class SalesActivity: BaseActivity() {
    var back: ImageButton? = null
    var rvSales: RecyclerView? = null
    var salesAdapter: SalesAdapter? = null
    var salesList: ArrayList<Sales>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sales)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        rvSales = findViewById(R.id.rv_sales)

        salesList!!.add(Sales("A-112", "Mon, 30 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-113", "Mon, 30 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-114", "Mon, 30 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-115", "Mon, 30 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-116", "Mon, 30 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-117", "Mon, 31 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-118", "Mon, 31 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-119", "Mon, 31 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-1110", "Mon, 31 Desember 2020", "Finish"))
        salesList!!.add(Sales("A-1111", "Mon, 01 Januari 2021", "Finish"))
        salesList!!.add(Sales("A-1112", "Mon, 01 Januari 2021", "Finish"))
        salesList!!.add(Sales("A-1113", "Mon, 08 Januari 2021", "Finish"))

        //Set up Adapter
        salesAdapter = SalesAdapter(this, salesList!!)
        rvSales?.setLayoutManager(LinearLayoutManager(this))
        rvSales?.setAdapter(salesAdapter)
        rvSales?.setHasFixedSize(false)

        initActionButton()
    }

    private fun initActionButton() {
        back!!.onClick { finish() }
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
}