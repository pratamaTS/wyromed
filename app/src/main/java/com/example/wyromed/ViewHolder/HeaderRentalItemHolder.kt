package com.example.wyromed.ViewHolder

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.wyromed.R

class HeaderRentalItemHolder(row: View?, context: Context) {
    val title: TextView
    val vContext: Context

    init {
        title = row!!.findViewById(R.id.tv_dialog_rental_item)
        vContext = row!!.context
    }
}