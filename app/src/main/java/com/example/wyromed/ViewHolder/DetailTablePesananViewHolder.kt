package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class DetailTablePesananViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    var namaBarang: TextView? = null
    var jumlahBarang:TextView? = null

    init{
        namaBarang = itemView.findViewById(R.id.tv_nama_barang_field_pesanan)
        jumlahBarang = itemView.findViewById(R.id.tv_jumlah_barang_field_pesanan)
    }
}