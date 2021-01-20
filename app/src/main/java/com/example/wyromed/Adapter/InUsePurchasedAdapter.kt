package com.example.wyromed.Adapter

import android.content.Context
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.Chronometer.OnChronometerTickListener
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.InUsePurchasedViewHolder


class InUsePurchasedAdapter(
    private val context: Context,
    inUseItemList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<InUsePurchasedViewHolder>() {
    private val inUseItemList: ArrayList<HandoverPurchasedItem>
    var resume: Boolean = false
    var elapsedTime: Long = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InUsePurchasedViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_handover_list_purchased_item, parent, false)
        return InUsePurchasedViewHolder(v)
    }

    override fun onBindViewHolder(holder: InUsePurchasedViewHolder, position: Int) {
        holder.tvNamaBarangPurchased.setText(inUseItemList[position].name)
        holder.tvTitleStatus.setVisibility(View.VISIBLE)
        holder.tvStatus.setVisibility(View.VISIBLE)

        holder.tvStatus.text = "ON PROGRESS"
    }

    override fun getItemCount(): Int {
        return inUseItemList.size
    }

    init {
        this.inUseItemList = inUseItemList
    }
}