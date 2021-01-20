package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.ListBookingItem
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ListBookingItemViewHolder

class ListBookingRItemAdapter(private var mContext: Context, mListBookingPurchasedItem: ArrayList<RentalItem>) :
    RecyclerView.Adapter<ListBookingItemViewHolder>() {
    private val mListBookingPurchasedItem: ArrayList<RentalItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookingItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_booking, parent, false)
        return ListBookingItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListBookingItemViewHolder, position: Int) {
        holder.tvItemName.setText(mListBookingPurchasedItem[position].product_name)
        holder.tvDateSuccess.setText(mListBookingPurchasedItem[position].start_date)
        holder.tvTimeSuccess.setText(mListBookingPurchasedItem[position].start_time)
    }

    override fun getItemCount(): Int {
        return mListBookingPurchasedItem.size
    }

    fun removeItem(position: Int) {
        mListBookingPurchasedItem.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(rentalItem: RentalItem, position: Int) {
        mListBookingPurchasedItem.add(position, rentalItem)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListBookingPurchasedItem = mListBookingPurchasedItem
    }
}