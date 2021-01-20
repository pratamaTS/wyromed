package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R
import org.jetbrains.anko.find

class ListHandoverRentalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangRental: TextView
    var tvTitleJumlahBarangRental: TextView
    var tvJumlahBarangRental: TextView
    var tvTitleOperation: TextView
    var tvStartOperation: TextView

    init {
        tvNamaBarangRental = itemView.findViewById(R.id.tv_nama_barang_rental_order)
        tvTitleJumlahBarangRental = itemView.findViewById(R.id.tv_title_amount_qty_rental_order)
        tvJumlahBarangRental = itemView.findViewById(R.id.tv_amount_qty_rental_order)
        tvTitleOperation = itemView.findViewById(R.id.tv_title_operation_rental_order)
        tvStartOperation = itemView.findViewById(R.id.tv_start_rental_order)
    }
}