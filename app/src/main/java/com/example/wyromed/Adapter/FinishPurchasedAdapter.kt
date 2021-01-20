package com.example.wyromed.Adapter

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.FinishPurchasedViewHolder
import java.lang.String

class FinishPurchasedAdapter(
    private val context: Context,
    finishPurchaseItemList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<FinishPurchasedViewHolder>() {
    private val finishPurchaseItemList: ArrayList<HandoverPurchasedItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishPurchasedViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_purchased_item_finish, parent, false)
        return FinishPurchasedViewHolder(v)
    }

    override fun onBindViewHolder(holder: FinishPurchasedViewHolder, position: Int) {
        holder.tvNamaBarangPurchased.text = finishPurchaseItemList[position].name
        try {
            holder.tvJumlahBarang.setText(String.valueOf(finishPurchaseItemList[position].quantity))
        } catch (ex: NotFoundException) {
            holder.tvJumlahBarang.setText("0")
        }
    }

    override fun getItemCount(): Int {
        return finishPurchaseItemList.size
    }

    init {
        this.finishPurchaseItemList = finishPurchaseItemList
    }
}