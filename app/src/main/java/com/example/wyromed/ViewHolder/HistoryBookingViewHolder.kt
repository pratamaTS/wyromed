package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class HistoryBookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNoOrder: TextView
    var tvHistoryBookingDate: TextView

    init {
        tvNoOrder = itemView.findViewById(R.id.tv_no_order_history_booking)
        tvHistoryBookingDate = itemView.findViewById(R.id.tv_date_history_booking)
    }
}