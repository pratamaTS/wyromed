package com.example.wyromed.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Adapter.HistorySalesAdapter
import com.example.wyromed.Model.HistorySales
import com.example.wyromed.R
import com.example.wyromed.Response.Order.DataOrder
import org.jetbrains.anko.support.v4.toast
import java.util.*

class HistorySalesFragment : Fragment() {
    var historySalesList: MutableList<HistorySales>? = null
    var historySalesSearchList: MutableList<HistorySales>? = null
    var rvHistorySales: RecyclerView? = null
    var adapter: HistorySalesAdapter? = null
    var tokenType: String? = null
    var token: String? = null
    var searchHistorySales: androidx.appcompat.widget.SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_history_sales, container, false)

        //INIT VIEW
        rvHistorySales = view.findViewById(R.id.rv_history_sales)
        searchHistorySales = view.findViewById(R.id.search_history_sales)

        getHistorySales()

        return view
    }

    private fun getHistorySales(){
        tokenType = arguments?.getString("token_type")
        token = arguments?.getString("token")

        if(tokenType==null || token==null){
            toast("Gagal mengambil data")?.show()
            activity?.finish()
        } else {
            //List History
            historySalesList = ArrayList<HistorySales>()
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))
            historySalesList!!.add(HistorySales("A-112", "Mon, 29 July 2020"))

            historySalesSearchList?.addAll(historySalesList!!)

            adapter = historySalesSearchList?.let { HistorySalesAdapter(this, it) }
            rvHistorySales!!.setLayoutManager(LinearLayoutManager(context))
            rvHistorySales!!.setAdapter(adapter)
            rvHistorySales!!.setHasFixedSize(false)

            searchHistorySales?.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        historySalesSearchList!!.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        historySalesList!!.forEach {
                            if(it.noSales!!.toLowerCase(Locale.getDefault()).contains(search)){
                                historySalesSearchList!!.add(it)
                            }
                        }

                        rvHistorySales?.adapter!!.notifyDataSetChanged()
                    }else{
                        historySalesSearchList!!.clear()
                        historySalesSearchList!!.addAll(historySalesList!!)
                        rvHistorySales?.adapter!!.notifyDataSetChanged()
                    }
                    return true
                }

            })
        }
    }
}