package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class HistorySalesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNoSales: TextView
    var tvHistorySalesDate: TextView

    init {
        tvNoSales = itemView.findViewById(R.id.tv_no_order_history_sales)
        tvHistorySalesDate = itemView.findViewById(R.id.tv_date_history_sales)
    }
}