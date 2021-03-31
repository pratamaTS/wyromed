package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ConfirmRentalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangRental: TextView
    var tvStatusConfirmRental: TextView

    init {
        tvNamaBarangRental = itemView.findViewById(R.id.tv_nama_barang_rental_order)
        tvStatusConfirmRental = itemView.findViewById(R.id.tv_status_rental_order)
    }
}