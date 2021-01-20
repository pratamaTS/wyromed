package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ListHandoverPurchasedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangBeli: TextView
    var tvTitleJumlahBarangBeli: TextView
    var tvJumlahBarangBeli: TextView
    var tvTitleStatus: TextView
    var tvAccepted: TextView

    init {
        tvNamaBarangBeli = itemView.findViewById(R.id.tv_nama_barang_purchased_order)
        tvTitleJumlahBarangBeli = itemView.findViewById(R.id.tv_title_amount_qty_purchased_order)
        tvJumlahBarangBeli = itemView.findViewById(R.id.tv_amount_qty_purchased_order)
        tvTitleStatus = itemView.findViewById(R.id.tv_title_status_purchased_order)
        tvAccepted = itemView.findViewById(R.id.tv_status_purchased_order)
    }
}