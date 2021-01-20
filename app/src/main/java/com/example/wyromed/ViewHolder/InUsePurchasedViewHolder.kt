package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class InUsePurchasedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangPurchased: TextView
    var tvTitleOperation: TextView
    var tvCountTimer: Chronometer

    init {
        tvNamaBarangPurchased = itemView.findViewById(R.id.tv_nama_barang_purchased_order)
        tvTitleOperation = itemView.findViewById(R.id.tv_title_operation_purchased_order)
        tvCountTimer = itemView.findViewById(R.id.tv_stopwatch_purchased_order)
    }
}