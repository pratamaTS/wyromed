package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.StockRequest
import com.example.wyromed.Model.StockRequestItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.PenguranganRequestViewHolder
import java.lang.String
import java.util.ArrayList

class PenguranganRequestAdapter(context: Context?, stockRequestList: ArrayList<StockRequestDetails?>?) :
    RecyclerView.Adapter<PenguranganRequestViewHolder>() {
    private var context: Context? = null
    private var stockRequestList: ArrayList<StockRequestDetails?>? = null

    init {
        this.context = context
        this.stockRequestList = stockRequestList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenguranganRequestViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_stock_request, parent, false)
        return PenguranganRequestViewHolder(view)
    }

    override fun onBindViewHolder(holder: PenguranganRequestViewHolder, position: Int) {
        holder.tvNamaBarang.setText(stockRequestList!![position]!!.productName)
        holder.tvJumlahRequest.setText(String.valueOf(stockRequestList!![position]!!.quantity))
    }

    override fun getItemCount(): Int {
        return stockRequestList!!.size
    }
}