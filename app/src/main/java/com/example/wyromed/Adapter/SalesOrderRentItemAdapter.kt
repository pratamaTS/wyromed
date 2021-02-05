package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.HandoverRentalItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.SalesOrderRentalItemViewHolder
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.lang.String
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class SalesOrderRentItemAdapter(
    private val context: Context,
    private val setTotalPrice: TotalPriceListenerRental,
    salesRentItemList: ArrayList<HandoverRentalItem>
) :
    RecyclerView.Adapter<SalesOrderRentalItemViewHolder>() {

    val localeID =  Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    var qty: Int = 0
    var totalOverAll: Int = 0
    private val salesRentItemList: ArrayList<HandoverRentalItem>

    interface TotalPriceListenerRental {
        fun totalPriceListenerRental(totalPrice: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SalesOrderRentalItemViewHolder {
        val view: View = LayoutInflater.from(context)
            .inflate(R.layout.item_list_detail_sales_item, parent, false)
        return SalesOrderRentalItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SalesOrderRentalItemViewHolder, position: Int) {
        holder.namaBarang.setText(salesRentItemList[position].product_name)

        holder.hargaBarang.text = "Rp 0"
        holder.kuantitasBarang.setText(String.valueOf(salesRentItemList[position].quantity))
        qty = salesRentItemList[position].quantity

        holder.totalHarga.text = "Rp 0"

        holder.hargaBarang.onClick {
            var view = holder.itemView
            val context = view.context
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as? LayoutInflater
            if (inflater != null) {
                view = inflater.inflate(
                    R.layout.layout_bottom_sheet_edit_price,
                    view.findViewById<View>(R.id.bottom_sheet_container_edit) as? LinearLayout
                )
            }
            val hargaBarangBot = view.findViewById<EditText>(R.id.et_edit_price)
            val btnSave = view.findViewById<Button>(R.id.btn_save_new_price)
            val bottomSheetDialog = BottomSheetDialog(context, R.style.BottomSheetDialogTheme)
            btnSave.setOnClickListener { v ->
                holder.hargaBarang.text = numberFormat.format(hargaBarangBot.text.toString().toInt())

                val totalHargaIt = hargaBarangBot.text.toString().toInt() * qty
                holder.totalHarga.text = numberFormat.format(totalHargaIt)
                totalOverAll = totalOverAll + totalHargaIt

                setTotalPrice.totalPriceListenerRental(totalOverAll)
                bottomSheetDialog.dismiss()
            }
            bottomSheetDialog.setContentView(view)
            bottomSheetDialog.setCancelable(true)
            bottomSheetDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return salesRentItemList.size
    }

    init {
        this.salesRentItemList = salesRentItemList
    }
}