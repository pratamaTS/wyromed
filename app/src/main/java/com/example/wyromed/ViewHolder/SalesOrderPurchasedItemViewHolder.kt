package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class SalesOrderPurchasedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var namaBarang: TextView
    var hargaBarang: TextView
    var kuantitasBarang: TextView
    var totalHarga: TextView

    init {
        namaBarang = itemView.findViewById(R.id.tv_sales_item_name)
        hargaBarang = itemView.findViewById(R.id.tv_sales_item_price)
        kuantitasBarang = itemView.findViewById(R.id.tv_sales_kuantitas_item)
        totalHarga = itemView.findViewById(R.id.tv_sales_total_price_item)
    }
}