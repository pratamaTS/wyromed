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
    var counttab: Int
) :
    FragmentStatePagerAdapter(fm!!) {
    override fun getItem(position: Int): Fragment {
        var historyBookingFragment: HistoryBookingFragment = HistoryBookingFragment()
        var historySalesFragment: HistorySalesFragment = HistorySalesFragment()
        var historyStockFragment: HistoryStockFragment = HistoryStockFragment()

        return when (position) {
            0 -> historyBookingFragment
            1 -> historySalesFragment
            2 -> historyStockFragment
            else -> historyBookingFragment
        }
    }

    override fun getCount(): Int {
        return counttab
    }
}