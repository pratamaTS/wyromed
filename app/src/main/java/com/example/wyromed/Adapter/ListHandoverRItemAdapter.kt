package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ListHandoverRentalItemViewHolder
import java.lang.String

class ListHandoverRItemAdapter(
    private var mContext: Context,
    orderRentalItemList: ArrayList<HandoverRentalItem>
) :
    RecyclerView.Adapter<ListHandoverRentalItemViewHolder>() {
    private val orderRentalItemList: ArrayList<HandoverRentalItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHandoverRentalItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_handover_list_rental_item, parent, false)
        return ListHandoverRentalItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListHandoverRentalItemViewHolder, position: Int) {
        holder.tvNamaBarangRental.text = orderRentalItemList[position].product_name
        holder.tvJumlahBarangRental.text = orderRentalItemList[position].quantity.toString()

        holder.tvTitleJumlahBarangRental.visibility = View.VISIBLE
        holder.tvJumlahBarangRental.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return orderRentalItemList.size
    }

    fun removeItem(position: Int) {
        orderRentalItemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(rentalItem: HandoverRentalItem, position: Int) {
        orderRentalItemList.add(position, rentalItem)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.orderRentalItemList = orderRentalItemList
    }
}