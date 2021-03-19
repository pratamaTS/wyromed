package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.PurchasedItem
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.DetailBookingItemViewHolder

class ListDetailBookingPurchasedItemAdapter(private var mContext: Context, mListDetailBookingPurchasedItem: ArrayList<PurchasedItem>) :
    RecyclerView.Adapter<DetailBookingItemViewHolder>() {
        private val mListDetailBookingPurchasedItem: ArrayList<PurchasedItem>
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBookingItemViewHolder {
            val v: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_detail_booking_item, parent, false)
            return DetailBookingItemViewHolder(v)
        }

        override fun onBindViewHolder(holder: DetailBookingItemViewHolder, position: Int) {
            holder.itemName.setText(mListDetailBookingPurchasedItem[position].name)
            holder.qty.setText(mListDetailBookingPurchasedItem[position].quantity.toString())
//            holder.dateDetail.setText(mListDetailBookingPurchasedItem[position].start_date)
//            holder.timeDetail.setText(mListDetailBookingPurchasedItem[position].start_time)
        }

        override fun getItemCount(): Int {
            return mListDetailBookingPurchasedItem.size
        }

        fun removeItem(position: Int) {
            mListDetailBookingPurchasedItem.removeAt(position)
            notifyItemRemoved(position)
        }

        fun restoreItem(rentalItem: PurchasedItem, position: Int) {
            mListDetailBookingPurchasedItem.add(position, rentalItem)
            notifyItemInserted(position)
        }

        init {
            this.mContext = mContext
            this.mListDetailBookingPurchasedItem = mListDetailBookingPurchasedItem
        }
}