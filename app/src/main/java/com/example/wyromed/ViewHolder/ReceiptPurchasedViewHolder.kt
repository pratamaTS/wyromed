package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ReceiptPurchasedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangBeli: TextView
    var tvTitleJumlahBarangDipakai: TextView
    var tvJumlahBarangDipakai: TextView

    init {
        tvNamaBarangBeli = itemView.findViewById(R.id.tv_nama_barang_purchased_order)
        tvTitleJumlahBarangDipakai = itemView.findViewById(R.id.tv_title_amount_qty_purchased_order)
        tvJumlahBarangDipakai = itemView.findViewById(R.id.tv_amount_qty_purchased_order)
    }
}