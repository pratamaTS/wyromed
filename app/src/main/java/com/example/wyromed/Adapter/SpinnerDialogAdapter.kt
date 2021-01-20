package com.example.wyromed.Adapter

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.wyromed.Activity.PatientActivity
import com.example.wyromed.Model.Patient
import com.example.wyromed.R
import com.example.wyromed.Response.Patient.DataPatient
import com.example.wyromed.ViewHolder.HeaderHolder
import com.example.wyromed.ViewHolder.ItemHolder
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.ArrayList

class SpinnerDialogAdapter(var tokenType: String, var token: String, var context: Context, var dataSource: ArrayList<DataPatient>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var choose: Boolean = false

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View
        var vh: ItemHolder
        val hh: HeaderHolder
        var tvTitle: TextView

        if (convertView == null) {
            view = inflater.inflate(R.layout.background_spinner_dialog, parent, false)
            tvTitle = view.findViewById(R.id.tv_title)
            when(choose){
                false -> tvTitle.text = "Select Patient"
                true -> tvTitle.text = dataSource[position].name
            }
        } else {
            if(position == 0){
                view = inflater.inflate(R.layout.custom_spinner_patient, parent, false)

                hh = HeaderHolder(view, context)
                view?.tag = hh
                hh.headItem.text = dataSource[position].name

                val intent = Intent(context, PatientActivity::class.java)
                intent.putExtra("token_type", tokenType)
                intent.putExtra("token", token)

                hh.addButon.onClick { context.startActivity(intent) }
            }else {
                view = inflater.inflate(R.layout.item_list_spinner, parent, false)
                vh = ItemHolder(view)
                view?.tag = vh
                vh.item.text = dataSource[position].name
            }
        }

        val id = context.resources.getIdentifier(dataSource.get(position).toString(), "drawable", context.packageName)

        return view
    }

    override fun getItem(position: Int): DataPatient {
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