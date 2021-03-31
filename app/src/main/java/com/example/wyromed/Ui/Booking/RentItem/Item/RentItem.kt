package com.example.wyromed.Ui.Booking.RentItem.Item

import android.annotation.SuppressLint
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.R
import com.mikepenz.fastadapter.items.ModelAbstractItem

class RentItem(model: BookingOrderDetails) : ModelAbstractItem<BookingOrderDetails, RentItem.ViewHolder>(model)  {

    override val layoutRes: Int = R.layout.item_list_rental
    override val type: Int = R.id.item_rental

    override var identifier: Long = if(model.product_id == 0) -1 else model.product_id!!.toLong()

    override fun getViewHolder(v: View): ViewHolder {
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun bindView(holder: ViewHolder, payloads: List<Any>) {
        super.bindView(holder, payloads)

        val mModel = model

        holder.tvItemName.text = mModel.product_name
        holder.tvQty.text = mModel.quantity.toString()
    }

    override fun unbindView(holder: ViewHolder) {
        super.unbindView(holder)

        holder.tvItemName.text = null
        holder.tvQty.text = null
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tvItemName: TextView = view.findViewById(R.id.tv_rental_item_name)
        var tvQty: TextView = view.findViewById(R.id.tv_rental_item_amount)

    }
}
