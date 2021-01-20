package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class PenambahanRequestViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var tvNamaBarang: TextView? = null
    var tvJumlahRequest: TextView? = null

    init {
        tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang_stock_request)
        tvJumlahRequest = itemView.findViewById(R.id.tv_jumlah_stock_request)
    }
}