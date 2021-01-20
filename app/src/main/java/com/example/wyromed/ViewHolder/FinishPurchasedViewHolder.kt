package com.example.wyromed.ViewHolder

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class FinishPurchasedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvNamaBarangPurchased: TextView
    var tvJumlahBarang: TextView
    var minus: ImageButton? = null
    var plus: ImageButton? = null

    init {
        tvNamaBarangPurchased = itemView.findViewById(R.id.tv_nama_barang_purchased_order_finish)
        tvJumlahBarang = itemView.findViewById(R.id.tv_jumlah_barang_finish)
        tvJumlahBarang.text = "0"
    }
}