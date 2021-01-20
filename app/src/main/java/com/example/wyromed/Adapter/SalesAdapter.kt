package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Sales
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.SalesViewHolder

class SalesAdapter(private var mContext: Context, mListSalesItem: ArrayList<Sales>) :
    RecyclerView.Adapter<SalesViewHolder>() {
    private val mListSalesItem: ArrayList<Sales>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_sales, parent, false)
        return SalesViewHolder(v)
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        holder.tvNoOrder.setText(mListSalesItem[position].noOrder)
        holder.tvSalesDate.setText(mListSalesItem[position].salesDate)
        holder.tvStatus.setText(mListSalesItem[position].status)
    }

    override fun getItemCount(): Int {
        return mListSalesItem.size
    }

    fun removeItem(position: Int) {
        mListSalesItem.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: Sales, position: Int) {
        mListSalesItem.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListSalesItem = mListSalesItem
    }
}