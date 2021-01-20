package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class HistoryStockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNoRequest: TextView
    var tvHistoryStockDate: TextView
    var tvStatusRequestStock: TextView

    init {
        tvNoRequest = itemView.findViewById(R.id.tv_no_request_history_stock)
        tvHistoryStockDate = itemView.findViewById(R.id.tv_date_history_stock)
        tvStatusRequestStock = itemView.findViewById(R.id.tv_status_history_stock)
    }
}