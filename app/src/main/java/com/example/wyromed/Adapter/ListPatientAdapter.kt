package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.ListPatient
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.ListPatientViewHolder

class ListPatientAdapter(private var mContext: Context, mListPatient: java.util.ArrayList<String>) :
    RecyclerView.Adapter<ListPatientViewHolder>() {
    private val mListPatient: ArrayList<ListPatient>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListPatientViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_spinner, parent, false)
        return ListPatientViewHolder(v)
    }

    override fun onBindViewHolder(holder: ListPatientViewHolder, position: Int) {
        holder.tvPatientName?.setText(mListPatient[position].patientName)
    }

    override fun getItemCount(): Int {
        return mListPatient.size
    }

    init {
        this.mContext = mContext
        this.mListPatient = mListPatient as ArrayList<ListPatient>
    }
}