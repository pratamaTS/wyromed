package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class DetailBookingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val itemName: TextView
    val qty: TextView
//    val dateDetail: TextView
    val timeDetail: TextView

    init{
        itemName = itemView!!.findViewById(R.id.tv_detail_booking_item_name)
        qty = itemView!!.findViewById(R.id.tv_detail_booking_item_qty)
//        dateDetail = itemView!!.findViewById(R.id.tv_detail_booking_date)
        timeDetail = itemView!!.findViewById(R.id.tv_detail_booking_hours)
    }
}