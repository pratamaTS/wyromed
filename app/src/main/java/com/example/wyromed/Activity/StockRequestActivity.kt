package com.example.wyromed.Activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.example.wyromed.Adapter.PageAdapterStockRequest
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.StockRequestItem
import com.example.wyromed.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import org.jetbrains.anko.sdk25.coroutines.onClick

class StockRequestActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
        val PLUS = "plus"
        val MINUS = "minus"
    }

    var tabLayout: TabLayout? = null
    var back: ImageButton?= null
    var viewPager: ViewPager? = null
    var stockRequestItem: ArrayList<StockRequestDetails?>? = ArrayList()
    var stockRequestItemMin: ArrayList<StockRequestDetails?>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_request)

        //INIT VIEW
        tabLayout = findViewById(R.id.tab_stock_request)
        back = findViewById(R.id.ic_back)
        viewPager = findViewById(R.id.view_pager_stock_request)

        //init Tab Layout
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Penambahan"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Pengurangan"))

        user!!.token_type = intent.getStringExtra("token_type")
        user!!.token = intent.getStringExtra("token")
        stockRequestItem = intent.getParcelableArrayListExtra<StockRequestDetails>("plus")
        stockRequestItemMin = intent.getParcelableArrayListExtra<StockRequestDetails>("minus")

        Log.d("token type sr", user!!.token_type.toString())
        Log.d("token sr", user!!.token.toString())

        val pageAdapterStockRequest = PageAdapterStockRequest(
            user!!.token_type.toString(), user!!.token.toString(), stockRequestItem, stockRequestItemMin, this,
            supportFragmentManager, tabLayout!!.tabCount
        )

        viewPager?.setAdapter(pageAdapterStockRequest)
        viewPager?.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager?.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        // init Button
        initActionButton()
    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
        }
    }
}