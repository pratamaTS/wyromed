package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class InUseRentalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangRental: TextView
    var tvTitleOperation: TextView
    var tvCountTimer: Chronometer

    init {
        tvNamaBarangRental = itemView.findViewById(R.id.tv_nama_barang_rental_order)
        tvTitleOperation = itemView.findViewById(R.id.tv_title_operation_rental_order)
        tvCountTimer = itemView.findViewById(R.id.tv_stopwatch_rental_order)
    }
}