package com.example.wyromed.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Fragment.HistoryBookingFragment
import com.example.wyromed.R
import com.example.wyromed.Response.Inbox.DataInbox
import com.example.wyromed.Response.Order.DataOrder
import com.example.wyromed.ViewHolder.HistoryBookingViewHolder

class HistoryBookingAdapter(
    private val context: HistoryBookingFragment,
    historyBookingList: ArrayList<DataOrder>
) :
    RecyclerView.Adapter<HistoryBookingViewHolder>() {
    private val historyBookingList: ArrayList<DataOrder>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryBookingViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_history_booking, parent, false)
        return HistoryBookingViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistoryBookingViewHolder, position: Int) {
        holder.tvNoOrder.setText(historyBookingList[position].number)
        holder.tvHistoryBookingDate.setText(historyBookingList[position].weekday+", "+
                historyBookingList[position].day.toString()+" "+
                historyBookingList[position].month+" "+
                historyBookingList[position].year)
    }

    override fun getItemCount(): Int {
        return historyBookingList.size
    }

    fun removeItem(position: Int) {
        historyBookingList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: DataOrder, position: Int) {
        historyBookingList.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.historyBookingList = historyBookingList
    }
}