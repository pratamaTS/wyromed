package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Sales
import com.example.wyromed.R
import com.example.wyromed.Response.SalesOrder.GetAllSO.DataGetSalesOrder
import com.example.wyromed.ViewHolder.SalesViewHolder
import java.text.SimpleDateFormat

class SalesAdapter(private var mContext: Context, mListSalesItem: ArrayList<DataGetSalesOrder?>?) :
    RecyclerView.Adapter<SalesViewHolder>() {
    private val mListSalesItem: ArrayList<DataGetSalesOrder?>?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalesViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_sales, parent, false)
        return SalesViewHolder(v)
    }

    override fun onBindViewHolder(holder: SalesViewHolder, position: Int) {
        val date = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(mListSalesItem?.get(position)?.createdAt)
        val createdSo = SimpleDateFormat("dd MMMM yyyy HH:mm:ss").format(date)

        holder.tvNoOrder.setText(mListSalesItem?.get(position)?.soNumber)
        holder.tvSalesDate.setText(createdSo)
        holder.tvStatus.setText(mListSalesItem?.get(position)?.status)
    }

    override fun getItemCount(): Int {
        if (mListSalesItem != null) {
            return mListSalesItem.size
        }else return 0
    }

    fun removeItem(position: Int) {
        mListSalesItem?.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: DataGetSalesOrder, position: Int) {
        mListSalesItem?.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListSalesItem = mListSalesItem
    }
}