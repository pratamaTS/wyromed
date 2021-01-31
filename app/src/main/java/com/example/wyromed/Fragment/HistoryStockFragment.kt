package com.example.wyromed.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.HistoryStockRequestInterface
import com.example.wyromed.Activity.Presenter.HistoryStockRequestPresenter
import com.example.wyromed.Adapter.HistoryStockAdapter
import com.example.wyromed.R
import com.example.wyromed.Response.Order.DataOrder
import com.example.wyromed.Response.StockRequest.DataGetStockRequest
import org.jetbrains.anko.support.v4.toast
import java.util.*
import kotlin.collections.ArrayList

class HistoryStockFragment : Fragment(), HistoryStockRequestInterface {
    var historyStockList: ArrayList<DataGetStockRequest> = ArrayList()
    var historyStockSearchList: ArrayList<DataGetStockRequest> = ArrayList()
    var rvHistoryStock: RecyclerView? = null
    var adapter: HistoryStockAdapter? = null
    var searchHistoryStock: androidx.appcompat.widget.SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_history_stock, container, false)

        //INIT VIEW
        rvHistoryStock = view.findViewById(R.id.rv_history_stock)
        searchHistoryStock = view.findViewById(R.id.search_history_stock)

        getHistoryStockReq()

        return view
    }

    private fun getHistoryStockReq(){
        HistoryStockRequestPresenter(this).getAllHistoryStockRequest(requireContext())
    }

    override fun onSuccessGetHistoryStockRequest(
        message: String?,
        dataReqStock: ArrayList<DataGetStockRequest?>?
    ) {
        historyStockList = dataReqStock as ArrayList<DataGetStockRequest>

        historyStockSearchList.addAll(historyStockList)

        //List History
        adapter = HistoryStockAdapter(this, historyStockSearchList)
        rvHistoryStock!!.setLayoutManager(LinearLayoutManager(context))
        rvHistoryStock!!.setAdapter(adapter)
        rvHistoryStock!!.setHasFixedSize(false)

        searchHistoryStock?.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    historyStockSearchList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    historyStockList.forEach {
                        if(it.number!!.toLowerCase(Locale.getDefault()).contains(search)){
                            historyStockSearchList.add(it)
                        }
                    }

                    rvHistoryStock?.adapter!!.notifyDataSetChanged()
                }else{
                    historyStockSearchList.clear()
                    historyStockSearchList.addAll(historyStockList!!)
                    rvHistoryStock?.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    override fun onErrorGetHistoryStockRequest(msg: String?) {
        toast("Failed to get data history stock request")
    }
}