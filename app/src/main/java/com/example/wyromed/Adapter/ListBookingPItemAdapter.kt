package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.ListBookingItem
import com.example.wyromed.Model.PurchasedItem
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ListBookingItemViewHolder

class ListBookingPItemAdapter(private var mContext: Context, mListBookingPurchasedItem: ArrayList<PurchasedItem>) :
    RecyclerView.Adapter<ListBookingItemViewHolder>() {
    private val mListBookingPurchasedItem: ArrayList<PurchasedItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookingItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_booking, parent, false)
        return ListBookingItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListBookingItemViewHolder, position: Int) {
        holder.tvItemName.setText(mListBookingPurchasedItem[position].name)
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

    fun restoreItem(purchasedItem: PurchasedItem, position: Int) {
        mListBookingPurchasedItem.add(position, purchasedItem)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListBookingPurchasedItem = mListBookingPurchasedItem
    }
}