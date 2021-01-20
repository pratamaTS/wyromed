package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ListPurchasedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvPItemName: TextView
    var tvPTotalAmount: TextView

    init {
        tvPItemName = itemView.findViewById(R.id.tv_purchased_item_name)
        tvPTotalAmount = itemView.findViewById(R.id.tv_purchased_item_amount)
    }
}