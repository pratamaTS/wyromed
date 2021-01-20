package com.example.wyromed.Adapter

import android.content.Context
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.wyromed.R
import com.example.wyromed.Response.PurchasedItem.DataPurchasedItem
import com.example.wyromed.ViewHolder.HeaderRentalItemHolder
import com.example.wyromed.ViewHolder.BookItemHolder
import kotlin.collections.ArrayList

class SpinnerDialogItemAdapter(var context: Context, var dataSource: ArrayList<DataPurchasedItem>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        val vh: BookItemHolder
        if (convertView == null) {
            view = inflater.inflate(R.layout.book_item_list_spinner, parent, false)
            vh = BookItemHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as BookItemHolder
        }

        vh.item.text = dataSource[position].name

        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View
        val vh: BookItemHolder
        val hh: HeaderRentalItemHolder
        if (position == 0) {
            view = inflater.inflate(R.layout.custom_spinner_rental_item, parent, false)
            view.setOnClickListener {
                val root = parent.rootView
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK))
                root.dispatchKeyEvent(KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_BACK))
            }
            hh = HeaderRentalItemHolder(view, context)
            view?.tag = hh
        } else {
            view = inflater.inflate(R.layout.book_item_list_spinner, parent, false)
            vh = BookItemHolder(view)
            view?.tag = vh
            vh.item.text = dataSource[position].name
        }
        return view
    }

    override fun getItem(position: Int): DataPurchasedItem? {
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return dataSource[position].productId!!.toLong()
    }

    init {
        this.context = context
        this.dataSource = dataSource
    }
}