package com.example.wyromed.ViewHolder

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.ItemClickListener
import com.example.wyromed.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class SalesOrderRentalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var namaBarang: TextView
    var hargaBarang: TextView
    var kuantitasBarang: TextView
    var totalHarga: TextView
    var context: Context? = null

    init {
        namaBarang = itemView.findViewById(R.id.tv_sales_item_name)
        hargaBarang = itemView.findViewById(R.id.tv_sales_item_price)
        kuantitasBarang = itemView.findViewById(R.id.tv_sales_kuantitas_item)
        totalHarga = itemView.findViewById(R.id.tv_sales_total_price_item)
    }
}