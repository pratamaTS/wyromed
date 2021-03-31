package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class OperationPurchasedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangPurchased: TextView
    var tvJumlahKuntitas: TextView

    init {
        tvNamaBarangPurchased = itemView.findViewById(R.id.tv_nama_barang_purchased_order)
        tvJumlahKuntitas = itemView.findViewById(R.id.tv_amount_qty_purchased_order)
    }
}