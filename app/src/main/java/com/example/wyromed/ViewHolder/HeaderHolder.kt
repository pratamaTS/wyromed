package com.example.wyromed.ViewHolder

import android.content.Context
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.example.wyromed.R

class HeaderHolder(row: View?, context: Context) {
    val addButon: ImageButton
    val headItem: TextView
    val vContext: Context

    init {
        addButon = row!!.findViewById(R.id.icon_add)
        headItem = row!!.findViewById(R.id.list_patient_head)
        vContext = row!!.context
    }
}