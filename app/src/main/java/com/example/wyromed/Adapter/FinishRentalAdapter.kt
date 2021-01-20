package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.FinishRentalViewHolder

class FinishRentalAdapter(
    private val context: Context,
    finishRentalItemList: ArrayList<HandoverRentalItem>
) :
    RecyclerView.Adapter<FinishRentalViewHolder>() {
    private val finishRentalItemList: ArrayList<HandoverRentalItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishRentalViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_rental_item_finish, parent, false)
        return FinishRentalViewHolder(v)
    }

    override fun onBindViewHolder(holder: FinishRentalViewHolder, position: Int) {
        holder.tvNamaBarangRental.setText(finishRentalItemList[position].product_name)
        holder.tvStatusOperation.setText("FINISHED")
        holder.tvTotalTimer.setText("2 Hours; 20 Min")
    }

    override fun getItemCount(): Int {
        return finishRentalItemList.size
    }

    init {
        this.finishRentalItemList = finishRentalItemList
    }
}