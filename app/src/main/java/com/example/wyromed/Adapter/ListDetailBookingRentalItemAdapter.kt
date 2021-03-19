package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.DetailBookingItemViewHolder

class ListDetailBookingRentalItemAdapter(private var mContext: Context, mListDetailBookingRentalItem: ArrayList<RentalItem>) :
    RecyclerView.Adapter<DetailBookingItemViewHolder>() {
        private val mListDetailBookingRentalItem: ArrayList<RentalItem>
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailBookingItemViewHolder {
            val v: View =
                LayoutInflater.from(parent.context).inflate(R.layout.item_list_detail_booking_item, parent, false)
            return DetailBookingItemViewHolder(v)
        }

        override fun onBindViewHolder(holder: DetailBookingItemViewHolder, position: Int) {
            holder.itemName.setText(mListDetailBookingRentalItem[position].product_name)
            holder.qty.setText(mListDetailBookingRentalItem[position].quantity.toString())
//            holder.dateDetail.setText(mListDetailBookingRentalItem[position].start_date)
//            holder.timeDetail.setText(mListDetailBookingRentalItem[position].start_time)
        }

        override fun getItemCount(): Int {
            return mListDetailBookingRentalItem.size
        }

        fun removeItem(position: Int) {
            mListDetailBookingRentalItem.removeAt(position)
            notifyItemRemoved(position)
        }

        fun restoreItem(rentalItem: RentalItem, position: Int) {
            mListDetailBookingRentalItem.add(position, rentalItem)
            notifyItemInserted(position)
        }

        init {
            this.mContext = mContext
            this.mListDetailBookingRentalItem = mListDetailBookingRentalItem
        }
}