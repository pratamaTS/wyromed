package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ConfirmRentalItemViewHolder

class ConfirmRentalAdapter(
    private val context: Context,
    orderRentalItemList: ArrayList<HandoverRentalItem>
) :
    RecyclerView.Adapter<ConfirmRentalItemViewHolder>() {
    private val orderRentalItemList: ArrayList<HandoverRentalItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfirmRentalItemViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_rental_item, parent, false)
        return ConfirmRentalItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ConfirmRentalItemViewHolder, position: Int) {
        holder.tvNamaBarangRental.setText(orderRentalItemList[position].product_name)
        holder.tvStatusConfirmRental.setText("ACCEPTED")
    }

    override fun getItemCount(): Int {
        return orderRentalItemList.size
    }

    init {
        this.orderRentalItemList = orderRentalItemList
    }
}