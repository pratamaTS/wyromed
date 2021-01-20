package com.example.wyromed.ViewHolder

import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.ItemClickListener
import com.example.wyromed.Activity.StockActivity
import com.example.wyromed.Model.StockRequestItem
import com.example.wyromed.R
import com.example.wyromed.Response.Stock.DataStock
import org.jetbrains.anko.sdk25.coroutines.onClick

class StockViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var tvNamaBarang: TextView
    var tvJumlahInventory: TextView
    var tvSatuan: TextView
    var tvKuantitas: TextView
    var minus: ImageButton
    var plus: ImageButton
    var sharedPref: SharedPreferences? = null
    private var itemClickListener: ItemClickListener? = null
    var kuantitas = 0
    var plusStatus = false
    var stockList: ArrayList<DataStock> = ArrayList()
    private var stockRequestList: ArrayList<StockRequestItem> = ArrayList()

    override fun onClick(v: View) {

        when (v.id) {
            R.id.btn_plus -> {
                kuantitas = kuantitas + 1
                tvKuantitas.text = kuantitas.toString()

                Log.d("kuantitas tambah1+", tvKuantitas.text.toString())
                Log.d("stock_request_item_+Ad", stockRequestList.toString())

            }
            R.id.btn_minus -> {
                kuantitas = kuantitas - 1
                tvKuantitas.text = kuantitas.toString()
                Log.d("kuantitas kurang", tvKuantitas.text.toString())
            }
        }

        itemClickListener!!.onClick(v, adapterPosition, false)
    }

    init {

        tvNamaBarang = itemView.findViewById(R.id.tv_nama_barang_stock)
        tvJumlahInventory = itemView.findViewById(R.id.tv_jumlah_inventory_stock)
        tvSatuan = itemView.findViewById(R.id.tv_satuan_inventory_stock)
        tvKuantitas = itemView.findViewById(R.id.tv_kuantitas_stock)
        minus = itemView.findViewById(R.id.btn_minus)
        plus = itemView.findViewById(R.id.btn_plus)
        tvKuantitas.text = "0"
    }
}