package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.SalesOrderPurchasedItemViewHolder
import java.lang.String

class SalesOrderPurchasedItemAdapter(
    private val context: Context,
    salesPurchaseItemList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<SalesOrderPurchasedItemViewHolder>() {
    private val salesPurchaseItemList: ArrayList<HandoverPurchasedItem>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SalesOrderPurchasedItemViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_list_detail_sales_item, parent, false)
        return SalesOrderPurchasedItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SalesOrderPurchasedItemViewHolder, position: Int) {
        holder.namaBarang.setText(salesPurchaseItemList[position].name)
        holder.hargaBarang.setText("0")
        holder.kuantitasBarang.setText(String.valueOf(salesPurchaseItemList[position].quantity))
        holder.totalHarga.setText("0")
    }

    override fun getItemCount(): Int {
        return salesPurchaseItemList.size
    }

    init {
        this.salesPurchaseItemList = salesPurchaseItemList
    }
}