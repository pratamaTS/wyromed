package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class SalesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNoOrder: TextView
    var tvSalesDate: TextView
    var tvStatus: TextView

    init {
        tvNoOrder = itemView.findViewById(R.id.tv_order_sales)
        tvSalesDate = itemView.findViewById(R.id.tv_tgl_sales)
        tvStatus = itemView.findViewById(R.id.tv_status_sales)
    }
}
