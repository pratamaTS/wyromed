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
import com.example.wyromed.ViewHolder.OperationRentalViewHolder

class OperationRentalAdapter(private val context: Context, private val chronoTickListener: RentalChronoTickListener, operationList: ArrayList<HandoverRentalItem>) :
    RecyclerView.Adapter<OperationRentalViewHolder>() {
    private val operationList: ArrayList<HandoverRentalItem>
    var alreadyStart: Boolean = false
    var resume: Boolean = false
    var elapsedTime: Long = 0
    var hours: Long = 0
    var minutes: Long = 0
    var seconds: Long = 0

    interface RentalChronoTickListener {
        fun onRentalChronoTickListener(chronoRental: Chronometer, hours: Long, minutes: Long, second: Long, elapsedTime: Long)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationRentalViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_rental_operation, parent, false)
        return OperationRentalViewHolder(v)
    }

    override fun onBindViewHolder(holder: OperationRentalViewHolder, position: Int) {
        holder.tvNamaBarangRental.setText(operationList[position].product_name)

        holder.tvCountTimer.setOnChronometerTickListener(Chronometer.OnChronometerTickListener {
            if (!resume) {
                hours = (SystemClock.elapsedRealtime() / 3600000)
                minutes =
                    (SystemClock.elapsedRealtime() - holder.tvCountTimer.getBase()) / 1000 / 60
                seconds =
                    (SystemClock.elapsedRealtime() - holder.tvCountTimer.getBase()) / 1000 % 60
                elapsedTime = elapsedTime
            } else {
                hours = (elapsedTime / 3600000)
                minutes = (elapsedTime - holder.tvCountTimer.getBase()) / 1000 / 60
                seconds = (elapsedTime - holder.tvCountTimer.getBase()) / 1000 % 60
                elapsedTime = elapsedTime + 1000
            }
        })


        when(alreadyStart) {
            false -> holder.tvCountTimer.start()
        }

        chronoTickListener.onRentalChronoTickListener(holder.tvCountTimer, hours, minutes, seconds, elapsedTime)
    }

    override fun getItemCount(): Int {
        return operationList.size
    }

    init {
        this.operationList = operationList
    }
}