package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R
import org.w3c.dom.Text

class BookedItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val noBook: TextView
    val orderDate: TextView
    val statusBook: TextView

    init{
        noBook = itemView!!.findViewById(R.id.tv_order_booking)
        orderDate = itemView!!.findViewById(R.id.tv_tgl_order_booking)
        statusBook = itemView!!.findViewById(R.id.tv_status_booking)
    }
}