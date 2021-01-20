package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.RentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ListRentalItemViewHolder

class ListRentalItemAdapter(private var mContext: Context, mListRentalItem: java.util.ArrayList<RentalItem>) :
    RecyclerView.Adapter<ListRentalItemViewHolder>() {
    private val mListRentalItem: ArrayList<RentalItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRentalItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_rental, parent, false)
        return ListRentalItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListRentalItemViewHolder, position: Int) {
        holder.tvItemName.setText(mListRentalItem[position].product_name)
        holder.tvTotalAmount.setText(mListRentalItem[position].quantity.toString())
    }

    override fun getItemCount(): Int {
        return mListRentalItem.size
    }

    fun removeItem(position: Int) {
        mListRentalItem.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: RentalItem, position: Int) {
        mListRentalItem.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListRentalItem = mListRentalItem
    }
}