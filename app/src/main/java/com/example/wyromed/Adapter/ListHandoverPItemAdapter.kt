package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ListHandoverPurchasedItemViewHolder
import com.example.wyromed.ViewHolder.ListHandoverRentalItemViewHolder

class ListHandoverPItemAdapter(
    private var mContext: Context,
    orderPurchasedItemList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<ListHandoverPurchasedItemViewHolder>() {
    private val orderPurchasedItemList: ArrayList<HandoverPurchasedItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHandoverPurchasedItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_handover_list_purchased_item, parent, false)
        return ListHandoverPurchasedItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListHandoverPurchasedItemViewHolder, position: Int) {
        holder.tvNamaBarangBeli.text = orderPurchasedItemList[position].name
        holder.tvJumlahBarangBeli.text = orderPurchasedItemList[position].quantity.toString()

        holder.tvTitleJumlahBarangBeli.visibility = View.VISIBLE
        holder.tvJumlahBarangBeli.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int {
        return orderPurchasedItemList.size
    }

    fun removeItem(position: Int) {
        orderPurchasedItemList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(purchasedItem: HandoverPurchasedItem, position: Int) {
        orderPurchasedItemList.add(position, purchasedItem)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.orderPurchasedItemList = orderPurchasedItemList
    }
}