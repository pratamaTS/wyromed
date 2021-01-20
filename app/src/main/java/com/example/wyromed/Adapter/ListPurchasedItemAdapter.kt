package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.ListPurchasedItem
import com.example.wyromed.Model.PurchasedItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ListPurchasedItemViewHolder

class ListPurchasedItemAdapter(private var mContext: Context, mListPurchasedItem: ArrayList<PurchasedItem>) :
    RecyclerView.Adapter<ListPurchasedItemViewHolder>() {
    private val mListPurchasedItem: ArrayList<PurchasedItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPurchasedItemViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_purchased, parent, false)
        return ListPurchasedItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListPurchasedItemViewHolder, position: Int) {
        holder.tvPItemName.setText(mListPurchasedItem[position].name)
        holder.tvPTotalAmount.setText(mListPurchasedItem[position].quantity.toString())
    }

    override fun getItemCount(): Int {
        return mListPurchasedItem.size
    }

    fun removeItem(position: Int) {
        mListPurchasedItem.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: PurchasedItem, position: Int) {
        mListPurchasedItem.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListPurchasedItem = mListPurchasedItem
    }
}