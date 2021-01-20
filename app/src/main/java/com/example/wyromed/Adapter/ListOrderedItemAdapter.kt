package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R
import com.example.wyromed.Response.Booking.DataBooking
import com.example.wyromed.Response.Order.DataOrder
import com.example.wyromed.ViewHolder.BookedItemViewHolder

class ListOrderedItemAdapter(private var mContext: Context, mListOrderedItem: ArrayList<DataOrder>) :
    RecyclerView.Adapter<BookedItemViewHolder>() {
    private val mListOrderedItem: ArrayList<DataOrder>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_booked, parent, false)
        return BookedItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookedItemViewHolder, position: Int) {
        holder.noBook.setText(mListOrderedItem[position].number)
        holder.orderDate.setText(mListOrderedItem[position].weekday+", "+
                mListOrderedItem[position].day.toString()+" "+
                mListOrderedItem[position].month+" "+
                mListOrderedItem[position].year)
        holder.statusBook.setText(mListOrderedItem[position].status)
    }

    override fun getItemCount(): Int {
        return mListOrderedItem.size
    }

    fun removeItem(position: Int) {
        mListOrderedItem.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: DataOrder, position: Int) {
        mListOrderedItem.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListOrderedItem = mListOrderedItem
    }
}