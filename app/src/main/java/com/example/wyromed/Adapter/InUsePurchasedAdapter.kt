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
    private val chronoTickListener: PurchasedChronoTickListener,
    inUseItemList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<InUsePurchasedViewHolder>() {
    private val inUseItemList: ArrayList<HandoverPurchasedItem>
    var resume: Boolean = false
    var elapsedTime: Long = 0

    interface PurchasedChronoTickListener {
        fun onPurchasedChronoTickListener(chronoPurchased: Chronometer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InUsePurchasedViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_handover_list_purchased_item, parent, false)
        return InUsePurchasedViewHolder(v)
    }

    override fun onBindViewHolder(holder: InUsePurchasedViewHolder, position: Int) {
        holder.tvNamaBarangPurchased.setText(inUseItemList[position].name)
        holder.tvTitleOperation.setVisibility(View.VISIBLE)
        holder.tvCountTimer.setVisibility(View.VISIBLE)

        holder.tvCountTimer.setOnChronometerTickListener(OnChronometerTickListener {
            if (!resume) {
                val minutes: Long =
                    (SystemClock.elapsedRealtime() - holder.tvCountTimer.getBase()) / 1000 / 60
                val seconds: Long =
                    (SystemClock.elapsedRealtime() - holder.tvCountTimer.getBase()) / 1000 % 60
                elapsedTime = SystemClock.elapsedRealtime()
            } else {
                val minutes: Long = (elapsedTime - holder.tvCountTimer.getBase()) / 1000 / 60
                val seconds: Long = (elapsedTime - holder.tvCountTimer.getBase()) / 1000 % 60
                elapsedTime = elapsedTime + 1000
            }
        })

        holder.tvCountTimer.start()
        chronoTickListener.onPurchasedChronoTickListener(holder.tvCountTimer)
    }

    override fun getItemCount(): Int {
        return inUseItemList.size
    }

    init {
        this.inUseItemList = inUseItemList
    }
}