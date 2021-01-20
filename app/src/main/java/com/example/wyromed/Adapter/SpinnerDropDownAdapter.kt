package com.example.wyromed.Adapter

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.wyromed.Activity.PatientActivity
import com.example.wyromed.R
import com.example.wyromed.Response.Hospital.DataHospital
import com.example.wyromed.ViewHolder.HeaderHolder
import com.example.wyromed.ViewHolder.ItemHolder
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.ArrayList

class SpinnerDropDownAdapter(var context: Context, var dataSource: ArrayList<DataHospital>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var choose: Boolean = false

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        var tvTitle: TextView

        if (convertView == null) {
            view = inflater.inflate(R.layout.smart_material_spinner_dropdown_item_layout, parent, false)
            tvTitle = view.findViewById(R.id.tv_spinner_dropdown)
            when(choose){
                false -> tvTitle.text = "Select Hospital"
                true -> tvTitle.text = dataSource[position].name
            }
        } else {
            view = inflater.inflate(R.layout.smart_material_spinner_dropdown_item_layout, parent, false)
            tvTitle = view.findViewById(R.id.tv_spinner_dropdown)
            tvTitle.text = dataSource[position].name
        }

        val id = context.resources.getIdentifier(dataSource.get(position).toString(), "drawable", context.packageName)

        return view
    }

    override fun getItem(position: Int): DataHospital {
        choose = true
        return dataSource[position]
    }

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItemId(position: Int): Long {
        return dataSource[position].id!!.toLong()
    }

    init {
        this.context = context
        this.dataSource = dataSource
    }
}