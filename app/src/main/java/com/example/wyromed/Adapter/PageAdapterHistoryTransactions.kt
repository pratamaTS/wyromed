package com.example.wyromed.Adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.wyromed.Activity.HistoryTransactionsActivity
import com.example.wyromed.Fragment.HistoryBookingFragment
import com.example.wyromed.Fragment.HistorySalesFragment
import com.example.wyromed.Fragment.HistoryStockFragment

class PageAdapterHistoryTransactions(
    historyTransactionsActivity: HistoryTransactionsActivity?,
    fm: FragmentManager?,
    var counttab: Int,
    val tokenType: String,
    val token: String
) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        var bundle: Bundle = Bundle()
        var historyBookingFragment: HistoryBookingFragment = HistoryBookingFragment()
        var historySalesFragment: HistorySalesFragment = HistorySalesFragment()
        var historyStockFragment: HistoryStockFragment = HistoryStockFragment()
        bundle.putString("token_type", tokenType)
        bundle.putString("token", token)

        return when (position) {
            0 -> historyBookingFragment.apply { historyBookingFragment.arguments = bundle }
            1 -> historySalesFragment.apply { historySalesFragment.arguments = bundle }
            2 -> historyStockFragment.apply { historyStockFragment.arguments = bundle }
            else -> historyBookingFragment.apply { historyBookingFragment.arguments = bundle }
        }
    }

    override fun getCount(): Int {
        return counttab
    }
}