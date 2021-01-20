package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class InUsePurchasedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangPurchased: TextView
    var tvTitleStatus: TextView
    var tvStatus: TextView

    init {
        tvNamaBarangPurchased = itemView.findViewById(R.id.tv_nama_barang_purchased_order)
        tvTitleStatus = itemView.findViewById(R.id.tv_title_status_purchased_order)
        tvStatus = itemView.findViewById(R.id.tv_status_purchased_order)
    }
}