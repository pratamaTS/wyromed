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
import com.example.wyromed.Model.StockRequestItem
import com.example.wyromed.R
import com.example.wyromed.Response.Stock.DataStock
import com.example.wyromed.ViewHolder.StockViewHolder
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.lang.String

class StockAdapter(internal val callbackStockInterface: CallbackStockInterface, val context: Context, stockList: ArrayList<DataStock>) :
    RecyclerView.Adapter<StockViewHolder>() {
    private val stockList: ArrayList<DataStock>
    private var stockRequestList: ArrayList<StockRequestDetails?>? = ArrayList()
    private var stockRequestListMin: ArrayList<StockRequestDetails?>? = ArrayList()

    interface CallbackStockInterface {
        fun passDataCallback(stockAdd: ArrayList<StockRequestDetails?>?, stockMin: ArrayList<StockRequestDetails?>?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockViewHolder {
        val v: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_stock, parent, false)
        return StockViewHolder(v)
    }

    override fun onBindViewHolder(holder: StockViewHolder, position: Int) {
        var qtyMinus: Int = 0
        var indexStockAdd: Int = 0
        var indexStockMin: Int = 0
        var indexPlus: Int = 0
        var indexMin: Int = 0
        var qtyRealMin: Int = 0

        holder.tvNamaBarang.setText(stockList[position].name)
        holder.tvJumlahInventory.setText(String.valueOf(stockList[position].stockAvailable))
        holder.tvSatuan.setText("pcs")
        try {
            holder.tvKuantitas.setText("0")
        } catch (ex: NotFoundException) {
            holder.tvKuantitas.setText("0")
        }

        holder.plus.onClick {
            if(holder.kuantitas < stockList[position]!!.stockAvailable!!) {
                holder.kuantitas = holder.kuantitas + 1
                if(holder.kuantitas == 0){
                    qtyRealMin = 0
                }
                if (qtyMinus != 0) {
                    qtyMinus = qtyMinus - 1
                }
                holder.tvKuantitas.text = holder.kuantitas.toString()

                if (stockRequestList!!.isNotEmpty() && indexPlus == position && holder.kuantitas > 1) {
                    stockRequestList!!.find {
                        it!!.productId == stockList[position].productId!!.toInt()
                    }?.quantity = holder.kuantitas
                } else if (stockRequestListMin!!.isNotEmpty() && indexMin == position && qtyMinus > 0) {
                    stockRequestListMin!!.find {
                        it!!.productId == stockList[position].productId!!.toInt()
                    }?.quantity = qtyMinus
                } else if (stockRequestListMin!!.isNotEmpty() && indexMin == position && qtyMinus == 0) {
                    indexStockMin =
                        stockRequestListMin!!.indexOfFirst { it!!.productId == stockList[position].productId!!.toInt() }

                    stockRequestListMin?.removeAt(indexStockMin)
                    indexMin = 0
                } else {
                    stockRequestList?.add(
                        StockRequestDetails(
                            stockList[position].productId!!.toInt(),
                            holder.kuantitas,
                            stockList[position].name,
                            stockList[position].unitName,
                            stockList[position].entity
                        )
                    )

                    indexStockAdd =
                        stockRequestList!!.indexOfFirst { it!!.productId == stockList[position].productId!!.toInt() }

                    indexPlus = position
                }

                callbackStockInterface.passDataCallback(stockRequestList, stockRequestListMin)

                Log.d("kuantitas tambah adap", holder.tvKuantitas.text.toString())
                Log.d("qty kurang", qtyMinus.toString())
                Log.d("stock request listadap+", stockRequestList.toString())
                Log.d("stock request listadap-", stockRequestListMin.toString())
            }else{
                Toast.makeText(context, "This is the max quantity", Toast.LENGTH_SHORT).show()
            }
        }


        holder.minus.onClick {
            if(stockList[position]!!.stockAvailable!! > 0) {
                if(qtyRealMin < stockList[position]!!.stockAvailable!!) {
                    holder.kuantitas = holder.kuantitas - 1
                    if(holder.kuantitas < 0){
                        qtyRealMin = qtyRealMin+1
                    }
                    Log.d("qty real", qtyRealMin.toString())
                    holder.tvKuantitas.text = holder.kuantitas.toString()

                    if (stockRequestListMin!!.isNotEmpty() && indexMin == position && qtyMinus > 0) {
                        qtyMinus = qtyMinus + 1
                        stockRequestListMin!!.find {
                            it!!.productId == stockList[position].productId!!.toInt()
                        }?.quantity = qtyMinus
                    } else if (stockRequestList!!.isNotEmpty() && indexPlus == position && holder.kuantitas > 0) {
                        stockRequestList!!.find {
                            it!!.productId == stockList[position].productId!!.toInt()
                        }?.quantity = holder.kuantitas
                    } else if (stockRequestList!!.isNotEmpty() && indexPlus == position && holder.kuantitas == 0) {
                        indexStockAdd =
                            stockRequestList!!.indexOfFirst { it!!.productId == stockList[position].productId!!.toInt() }

                        stockRequestList?.removeAt(indexStockAdd)
                        indexPlus = 0
                    } else {
                        //Qty Minus
                        qtyMinus = qtyMinus + 1
                        stockRequestListMin?.add(
                            StockRequestDetails(
                                stockList[position].productId!!.toInt(),
                                qtyMinus,
                                stockList[position].name,
                                stockList[position].unitName,
                                stockList[position].entity
                            )
                        )
                        indexMin = position
                    }

                    callbackStockInterface.passDataCallback(stockRequestList, stockRequestListMin)
                    Log.d("kuantitas kurang", holder.tvKuantitas.text.toString())
                    Log.d("qty kurang", qtyMinus.toString())
                    Log.d("stock request listadap+", stockRequestList.toString())
                    Log.d("stock request listadap-", stockRequestListMin.toString())
                }else{
                    Toast.makeText(context, "This is the max quantity", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(context, "Out of stock", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return stockList.size
    }

    init {
        this.stockList = stockList
    }
}