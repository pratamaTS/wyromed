package com.example.wyromed.Adapter

import android.content.Context
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.InUseRentalViewHolder

class InUseRentalAdapter(private val context: Context, private val chronoTickListener: RentalChronoTickListener, inUseItemList: ArrayList<HandoverRentalItem>) :
    RecyclerView.Adapter<InUseRentalViewHolder>() {
    private val inUseItemList: ArrayList<HandoverRentalItem>
    var resume: Boolean = false
    var elapsedTime: Long = 0
    var minutes: Long = 0
    var seconds: Long = 0

    interface RentalChronoTickListener {
        fun onRentalChronoTickListener(chronoRental: Chronometer, minutes: Long, second: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InUseRentalViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_handover_list_rental_item, parent, false)
        return InUseRentalViewHolder(v)
    }

    override fun onBindViewHolder(holder: InUseRentalViewHolder, position: Int) {
        holder.tvNamaBarangRental.setText(inUseItemList[position].product_name)
        holder.tvTitleOperation.setVisibility(View.VISIBLE)
        holder.tvCountTimer.setVisibility(View.VISIBLE)

        holder.tvCountTimer.setOnChronometerTickListener(Chronometer.OnChronometerTickListener {
            if (!resume) {
                minutes =
                    (SystemClock.elapsedRealtime() - holder.tvCountTimer.getBase()) / 1000 / 60
                seconds =
                    (SystemClock.elapsedRealtime() - holder.tvCountTimer.getBase()) / 1000 % 60
                elapsedTime = SystemClock.elapsedRealtime()
            } else {
                minutes = (elapsedTime - holder.tvCountTimer.getBase()) / 1000 / 60
                seconds = (elapsedTime - holder.tvCountTimer.getBase()) / 1000 % 60
                elapsedTime = elapsedTime + 1000
            }
        })

        holder.tvCountTimer.start()
        chronoTickListener.onRentalChronoTickListener(holder.tvCountTimer, minutes, seconds)
    }

    override fun getItemCount(): Int {
        return inUseItemList.size
    }

    init {
        this.inUseItemList = inUseItemList
    }
}