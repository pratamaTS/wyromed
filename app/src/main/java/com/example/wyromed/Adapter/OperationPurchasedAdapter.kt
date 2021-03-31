package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.OperationPurchasedViewHolder
import java.lang.String

class OperationPurchasedAdapter(
    private val context: Context,
    operationItemList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<OperationPurchasedViewHolder>() {
    private val operationItemList: ArrayList<HandoverPurchasedItem>
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OperationPurchasedViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_purchased_item, parent, false)
        return OperationPurchasedViewHolder(v)
    }

    override fun onBindViewHolder(holder: OperationPurchasedViewHolder, position: Int) {
        holder.tvNamaBarangPurchased.setText(operationItemList[position].name)
        holder.tvJumlahKuntitas.setText(String.valueOf(operationItemList[position].quantity))
    }

    override fun getItemCount(): Int {
        return operationItemList.size
    }

    init {
        this.operationItemList = operationItemList
    }
}