package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ReceiptRentalViewHolder

class ReceiptRentalAdapter(
    private val context: Context,
    receiptRentalItemList: ArrayList<HandoverRentalItem>,
    var minutesOperation: Long,
    var secondsOperation: Long
) :
    RecyclerView.Adapter<ReceiptRentalViewHolder>() {
    private val receiptRentalItemList: ArrayList<HandoverRentalItem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReceiptRentalViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_rental_item_finish, parent, false)
        return ReceiptRentalViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReceiptRentalViewHolder, position: Int) {
        holder.tvNamaBarangRental.setText(receiptRentalItemList[position].product_name)
        holder.tvStatusOperation.setText("FINISHED")
        holder.tvTotalTimer.text =minutesOperation.toString() + "minutes; " + secondsOperation.toString() + "seconds"
    }

    override fun getItemCount(): Int {
        return receiptRentalItemList.size
    }

    init {
        this.receiptRentalItemList = receiptRentalItemList
    }
}