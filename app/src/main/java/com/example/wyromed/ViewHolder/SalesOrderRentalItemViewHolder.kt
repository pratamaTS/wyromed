package com.example.wyromed.ViewHolder

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.ItemClickListener
import com.example.wyromed.R
import com.google.android.material.bottomsheet.BottomSheetDialog

class SalesOrderRentalItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener {
    var namaBarang: TextView
    var hargaBarang: TextView
    var kuantitasBarang: TextView
    var totalHarga: TextView
    var context: Context? = null
    private var itemClickListener: ItemClickListener? = null
    private var onClickListener: View.OnClickListener? = null

    @SuppressLint("NonConstantResourceId")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_sales_item_price -> openBottomSheet(v)
        }
    }

    fun setItemClickListener(itemClickListener: ItemClickListener?) {
        this.itemClickListener = itemClickListener
    }

    fun setOnClickListener(onClickListener: View.OnClickListener?) {
        this.onClickListener = onClickListener
    }

    private fun openBottomSheet(view: View) {
        var view = view
        val context = view.context
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        view = inflater.inflate(
            R.layout.layout_bottom_sheet_edit_price,
            view.findViewById<View>(R.id.bottom_sheet_container_edit) as LinearLayout
        )
        val etPriceItem = view.findViewById<EditText>(R.id.et_edit_price)
        val btnSave = view.findViewById<Button>(R.id.btn_save_new_price)
        val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
        btnSave.setOnClickListener { v ->
            Toast.makeText(v.context, "Edit Price", Toast.LENGTH_SHORT).show()
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.setCancelable(true)
        bottomSheetDialog.show()
    }

    init {
        namaBarang = itemView.findViewById(R.id.tv_sales_item_name)
        hargaBarang = itemView.findViewById(R.id.tv_sales_item_price)
        kuantitasBarang = itemView.findViewById(R.id.tv_sales_kuantitas_item)
        totalHarga = itemView.findViewById(R.id.tv_sales_total_price_item)
        itemView.setOnClickListener(this)
        hargaBarang.setOnClickListener(this)
    }
}