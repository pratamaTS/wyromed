package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ProvinceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvStateName: TextView

    init {
        tvStateName = itemView.findViewById(android.R.id.text1)
    }
}
