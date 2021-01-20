package com.example.wyromed.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Fragment.HistoryStockFragment
import com.example.wyromed.R
import com.example.wyromed.Response.StockRequest.DataGetStockRequest
import com.example.wyromed.ViewHolder.HistoryStockViewHolder

class HistoryStockAdapter(private val context: HistoryStockFragment, historyStockList: ArrayList<DataGetStockRequest>) :
    RecyclerView.Adapter<HistoryStockViewHolder>() {
    private val historyStockList: ArrayList<DataGetStockRequest>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryStockViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_history_stock, parent, false)
        return HistoryStockViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryStockViewHolder, position: Int) {
        holder.tvNoRequest.setText(historyStockList[position].number)
        holder.tvHistoryStockDate.setText(historyStockList[position].weekday+", "+
                historyStockList[position].day.toString()+" "+
                historyStockList[position].month+" "+
                historyStockList[position].year)
        holder.tvStatusRequestStock.setText(historyStockList[position].status)
    }

    override fun getItemCount(): Int {
        return historyStockList.size
    }

    init {
        this.historyStockList = historyStockList
    }
}