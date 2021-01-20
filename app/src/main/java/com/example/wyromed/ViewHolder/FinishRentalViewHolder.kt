package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class FinishRentalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangRental: TextView
    var tvStatusOperation: TextView
    var tvTotalTimer: TextView

    init {
        tvNamaBarangRental = itemView.findViewById(R.id.tv_nama_barang_rental_order_finish)
        tvStatusOperation = itemView.findViewById(R.id.tv_status_operation_rental_order_finish)
        tvTotalTimer = itemView.findViewById(R.id.tv_total_timer_rental_order)
    }
}