package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ListBookingItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvItemName: TextView
    var tvAmountQty: TextView
    var viewSuccess: ConstraintLayout

    init {
        tvItemName = itemView.findViewById(R.id.tvItemStatus)
        tvAmountQty = itemView.findViewById(R.id.tvAmountQty)
        viewSuccess = itemView.findViewById(R.id.view_success)
    }
}