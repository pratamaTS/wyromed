package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.SalesOrderRentalItemViewHolder
import java.lang.String

class SalesOrderRentItemAdapter(
    private val context: Context,
    salesRentItemList: ArrayList<HandoverRentalItem>
) :
    RecyclerView.Adapter<SalesOrderRentalItemViewHolder>() {
    private val salesRentItemList: ArrayList<HandoverRentalItem>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SalesOrderRentalItemViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_list_detail_sales_item, parent, false)
        return SalesOrderRentalItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SalesOrderRentalItemViewHolder, position: Int) {
        holder.namaBarang.setText(salesRentItemList[position].product_name)
        holder.hargaBarang.setText("0")
        holder.kuantitasBarang.setText(String.valueOf(salesRentItemList[position].quantity))
        holder.totalHarga.setText("0")
    }

    override fun getItemCount(): Int {
        return salesRentItemList.size
    }

    init {
        this.salesRentItemList = salesRentItemList
    }
}