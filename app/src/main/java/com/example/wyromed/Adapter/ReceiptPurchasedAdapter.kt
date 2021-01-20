package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ReceiptPurchasedViewHolder
import java.lang.String

class ReceiptPurchasedAdapter(
    private val context: Context,
    orderPurchasedList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<ReceiptPurchasedViewHolder>() {
    private val orderPurchasedList: ArrayList<HandoverPurchasedItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptPurchasedViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_handover_list_purchased_item, parent, false)
        return ReceiptPurchasedViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReceiptPurchasedViewHolder, position: Int) {
        holder.tvNamaBarangBeli.setText(orderPurchasedList[position].name)
        holder.tvJumlahBarangDipakai.setText(String.valueOf(orderPurchasedList[position].quantity))
        holder.tvTitleJumlahBarangDipakai.setVisibility(View.VISIBLE)
        holder.tvJumlahBarangDipakai.setVisibility(View.VISIBLE)
    }

    override fun getItemCount(): Int {
        return orderPurchasedList.size
    }

    init {
        this.orderPurchasedList = orderPurchasedList
    }
}