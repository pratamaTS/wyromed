package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R

class ListMessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var tvTitle: TextView
    var tvDetail: TextView
    var tvTime: TextView
    var viewBackground: LinearLayout
    var viewForeground: LinearLayout

    init {
        tvTitle = itemView.findViewById(R.id.tv_list_title_message)
        tvDetail = itemView.findViewById(R.id.tv_list_detail_message)
        tvTime = itemView.findViewById(R.id.tv_list_time_message)
        viewBackground = itemView.findViewById(R.id.view_background)
        viewForeground = itemView.findViewById(R.id.view_foreground)
    }
}