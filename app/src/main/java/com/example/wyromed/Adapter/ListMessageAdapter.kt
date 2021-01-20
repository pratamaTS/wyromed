package com.example.wyromedapp.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.ListMessage
import com.example.wyromed.R
import com.example.wyromed.Response.Inbox.DataInbox
import com.example.wyromed.ViewHolder.ListMessageViewHolder
import java.util.*
import java.util.logging.Filter
import java.util.logging.LogRecord
import kotlin.collections.ArrayList

class ListMessageAdapter(private var mContext: Context, mListMessage: ArrayList<DataInbox>,
                         private val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<ListMessageViewHolder>() {
    private var mListMessage: ArrayList<DataInbox>

    interface CellClickListener {
        fun onCellClickListener(id: Int, choose: Int, title: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMessageViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_message, parent, false)
        return ListMessageViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListMessageViewHolder, position: Int) {
        val boID = mListMessage[position].boId
        val soID = mListMessage[position].soaId
        val reqID = mListMessage[position].requestId
        val title: String = mListMessage[position].title.toString()
        var choose: Int = 0

        holder.itemView.setOnClickListener {
            if(boID != null){
                choose = 1

                cellClickListener.onCellClickListener(boID, choose, title)
            }else if(soID != null){
                choose = 2
                cellClickListener.onCellClickListener(soID, choose, title)
            }else if (reqID != null) {
                choose = 3
                cellClickListener.onCellClickListener(reqID, choose, title)
            }
        }

        holder.tvTitle.setText(mListMessage[position].title)
        holder.tvDetail.setText(mListMessage[position].description)
        holder.tvTime.setText(mListMessage[position].time)
    }

    override fun getItemCount(): Int {
        return mListMessage.size
    }

    fun removeItem(position: Int) {
        mListMessage.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreItem(item: DataInbox, position: Int) {
        mListMessage.add(position, item)
        notifyItemInserted(position)
    }

    init {
        this.mContext = mContext
        this.mListMessage = mListMessage
    }
}