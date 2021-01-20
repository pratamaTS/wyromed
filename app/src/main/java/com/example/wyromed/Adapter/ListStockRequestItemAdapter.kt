package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.R
import com.example.wyromed.Response.Booking.DataBooking
import com.example.wyromed.Response.Order.DataOrder
import com.example.wyromed.Response.StockRequest.DataGetStockRequest
import com.example.wyromed.ViewHolder.BookedItemViewHolder

class ListStockRequestItemAdapter(private var mContext: Context, mListStockRequestItem: ArrayList<DataGetStockRequest>) :
    RecyclerView.Adapter<BookedItemViewHolder>() {
    private val mListStockRequestItem: ArrayList<DataGetStockRequest>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookedItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_booked, parent, false)
        return BookedItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: BookedItemViewHolder, position: Int) {
        holder.noBook.setText(mListStockRequestItem[position].number)
        holder.orderDate.setText(mListStockRequestItem[position].weekday+", "+
                mListStockRequestItem[position].day.toString()+" "+
                mListStockRequestItem[position].month+" "+
                mListStockRequestItem[position].year)
        holder.statusBook.setText(mListStockRequestItem[position].status)
    }

    override fun getItemCount(): Int {
        return mListStockRequestItem.size
    }

    fun removeItem(position: Int) {
        mListStockRequestItem.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: DataGetStockRequest, position: Int) {
        mListStockRequestItem.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListStockRequestItem = mListStockRequestItem
    }
}