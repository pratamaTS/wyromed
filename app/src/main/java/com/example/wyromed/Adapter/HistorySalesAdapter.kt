package com.example.wyromed.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Fragment.HistorySalesFragment
import com.example.wyromed.Model.HistorySales
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.HistorySalesViewHolder

class HistorySalesAdapter(private val context: HistorySalesFragment, historySalesList: List<HistorySales>) :
    RecyclerView.Adapter<HistorySalesViewHolder>() {
    private val historySalesList: List<HistorySales>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorySalesViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_history_sales, parent, false)
        return HistorySalesViewHolder(v)
    }

    override fun onBindViewHolder(holder: HistorySalesViewHolder, position: Int) {
        holder.tvNoSales.setText(historySalesList[position].noSales)
        holder.tvHistorySalesDate.setText(historySalesList[position].historySalesDate)
    }

    override fun getItemCount(): Int {
        return historySalesList.size
    }

    init {
        this.historySalesList = historySalesList
    }
}