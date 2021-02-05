package com.example.wyromed.Adapter

import android.content.Context
import android.content.res.Resources.NotFoundException
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Model.Body.StockRequestDetails
import com.example.wyromed.Model.Header.HandoverPurchasedItem
import com.example.wyromed.R
import com.example.wyromed.ViewHolder.FinishPurchasedViewHolder
import com.example.wyromed.ViewHolder.StockViewHolder
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.lang.String

class FinishPurchasedAdapter(
    private val context: Context,
    internal val callbackStockFinishInterface: CallbackStockFinishInterface,
    finishPurchaseItemList: ArrayList<HandoverPurchasedItem>
) :
    RecyclerView.Adapter<FinishPurchasedViewHolder>() {
    private var finishPurchaseItemList: ArrayList<HandoverPurchasedItem>

    interface CallbackStockFinishInterface {
        fun passDataFinishCallback(stockChange: ArrayList<HandoverPurchasedItem>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishPurchasedViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_purchased_item_finish, parent, false)
        return FinishPurchasedViewHolder(v)
    }

    override fun onBindViewHolder(holder: FinishPurchasedViewHolder, position: Int) {
        var qtyMinus: Int = 0
        var indexStockAdd: Int = 0
        var indexStockMin: Int = 0
        var indexPlus: Int = 0
        var indexMin: Int = 0
        var qtyRealMin: Int = 0
        var stockEdit: Int = 0

        holder.tvNamaBarangPurchased.setText(finishPurchaseItemList[position].name)
        holder.tvJumlahBarang.setText(String.valueOf(finishPurchaseItemList[position].quantity))
        stockEdit = finishPurchaseItemList[position].quantity

        holder.plus?.onClick {
            stockEdit = stockEdit + 1
            if (stockEdit == 0) {
                qtyRealMin = 0
            }
            if (qtyMinus != 0) {
                qtyMinus = qtyMinus - 1
            }

            if (finishPurchaseItemList!!.isNotEmpty() && indexPlus == position && stockEdit > 1) {
                finishPurchaseItemList!!.find {
                    it!!.productId == finishPurchaseItemList[position].productId!!.toInt()
                }?.quantity = stockEdit
            } else if (finishPurchaseItemList!!.isNotEmpty() && indexMin == position && qtyMinus > 0) {
                finishPurchaseItemList!!.find {
                    it!!.productId == finishPurchaseItemList[position].productId!!.toInt()
                }?.quantity = qtyMinus
            } else if (finishPurchaseItemList!!.isNotEmpty() && indexMin == position && qtyMinus == 0) {
                indexStockMin =
                    finishPurchaseItemList!!.indexOfFirst { it!!.productId == finishPurchaseItemList[position].productId!!.toInt() }

                finishPurchaseItemList?.removeAt(indexStockMin)
                indexMin = 0
            }

            holder.tvJumlahBarang.text = stockEdit.toString()
            callbackStockFinishInterface.passDataFinishCallback(finishPurchaseItemList)

            Log.d("kuantitas tambah adap", stockEdit.toString())
            Log.d("qty kurang", qtyMinus.toString())
            Log.d("finish stock", finishPurchaseItemList.toString())
        }

        holder.minus?.onClick {
            if(finishPurchaseItemList[position]!!.quantity!! > 0) {
                stockEdit = stockEdit - 1
                if(holder.tvJumlahBarang.text.toString().toInt() < 0){
                    qtyRealMin = qtyRealMin+1
                }
                Log.d("qty real", qtyRealMin.toString())

                if (finishPurchaseItemList!!.isNotEmpty() && indexMin == position && qtyMinus > 0) {
                    qtyMinus = qtyMinus + 1
                    finishPurchaseItemList!!.find {
                        it!!.productId == finishPurchaseItemList[position].productId!!.toInt()
                    }?.quantity = qtyMinus
                } else if (finishPurchaseItemList!!.isNotEmpty() && indexPlus == position && stockEdit > 0) {
                    finishPurchaseItemList!!.find {
                        it!!.productId == finishPurchaseItemList[position].productId!!.toInt()
                    }?.quantity = stockEdit
                } else if (finishPurchaseItemList!!.isNotEmpty() && indexPlus == position && stockEdit == 0) {
                    indexStockAdd =
                        finishPurchaseItemList!!.indexOfFirst { it!!.productId == finishPurchaseItemList[position].productId!!.toInt() }

                    finishPurchaseItemList?.removeAt(indexStockAdd)
                    indexPlus = 0
                }

                holder.tvJumlahBarang.text = stockEdit.toString()
                callbackStockFinishInterface.passDataFinishCallback(finishPurchaseItemList)
                Log.d("kuantitas kurang", stockEdit.toString())
                Log.d("qty kurang", qtyMinus.toString())
                Log.d("finish stock", finishPurchaseItemList.toString())
            }else{
                Toast.makeText(context, "There is no stock", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return finishPurchaseItemList.size
    }

    init {
        this.finishPurchaseItemList = finishPurchaseItemList
    }
}