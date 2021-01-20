package com.example.wyromed.ViewHolder

import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.wyromed.R

class ItemHolder(row: View?) {
    val item: TextView

    init {
        item = row!!.findViewById(R.id.list_patient)
    }
}