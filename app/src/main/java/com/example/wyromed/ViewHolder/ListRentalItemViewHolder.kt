package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ListRentalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvItemName: TextView
    var tvTotalAmount: TextView

    init {
        tvItemName = itemView.findViewById(R.id.tv_rental_item_name)
        tvTotalAmount = itemView.findViewById(R.id.tv_rental_item_amount)
    }
}