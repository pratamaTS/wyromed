package com.example.wyromed.Activity

import android.os.Bundle
import android.widget.ImageButton
import androidx.viewpager.widget.ViewPager
import com.example.wyromed.Activity.StockActivity.*
import com.example.wyromed.Adapter.PageAdapterHistoryTransactions
import com.example.wyromed.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HistoryTransactionsActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var back: ImageButton? = null
    var message: String?  = null
    var tabLayout: TabLayout?  = null
    var viewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_transactions)

        //INIT VIEW
        back = findViewById(R.id.ic_back)
        tabLayout = findViewById(R.id.tab_history_transactions)
        viewPager = findViewById(R.id.view_pager_history_transactions)

        //Set Tab Layout
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Booking"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Sales"))
        tabLayout!!.addTab(tabLayout!!.newTab().setText("Stock"))

        val pageAdapterHistoryTransactions = PageAdapterHistoryTransactions(
            this,
            supportFragmentManager, tabLayout!!.tabCount
        )
        viewPager!!.setAdapter(pageAdapterHistoryTransactions)
        viewPager!!.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))

        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager!!.setCurrentItem(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        if(message != null){
            toast(message.toString()).show()
        }

        // init Button
        initActionButton()

    }

    private fun initActionButton() {
        back!!.onClick {
            finish()
        }
    }
}