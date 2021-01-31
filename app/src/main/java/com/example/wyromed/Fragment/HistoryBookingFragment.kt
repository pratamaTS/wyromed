package com.example.wyromed.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wyromed.Activity.Interface.HistoryBookingInterface
import com.example.wyromed.Activity.Presenter.HistoryBookingPresenter
import com.example.wyromed.Adapter.HistoryBookingAdapter
import com.example.wyromed.R
import com.example.wyromed.Response.Order.DataOrder
import org.jetbrains.anko.support.v4.toast
import java.util.*
import kotlin.collections.ArrayList

class HistoryBookingFragment : Fragment(), HistoryBookingInterface {
    var historyBookingList: ArrayList<DataOrder> = ArrayList()
    var historyBookingSearchList: ArrayList<DataOrder> = ArrayList()
    var rvHistoryBooking: RecyclerView? = null
    var adapter: HistoryBookingAdapter? = null
    var searchHistoryBooking: androidx.appcompat.widget.SearchView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_history_booking, container, false)

        //INIT VIEW
        rvHistoryBooking = view.findViewById(R.id.rv_history_booking)
        searchHistoryBooking = view.findViewById(R.id.search_history_booking)

        getHistoryBooking()

        return view
    }

    private fun getHistoryBooking(){
        HistoryBookingPresenter(this).getAllHistoryBooking(requireContext())
    }

    override fun onSuccessHistoryBooking(dataOrdered: ArrayList<DataOrder?>?) {
        historyBookingList = dataOrdered as ArrayList<DataOrder>

        historyBookingSearchList.addAll(historyBookingList)

        //List History
        adapter = HistoryBookingAdapter(this, historyBookingSearchList!!)
        rvHistoryBooking!!.setLayoutManager(LinearLayoutManager(context))
        rvHistoryBooking!!.setAdapter(adapter)
        rvHistoryBooking!!.setHasFixedSize(false)

        searchHistoryBooking?.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText!!.isNotEmpty()){
                    historyBookingSearchList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    historyBookingList.forEach {
                        if(it.number!!.toLowerCase(Locale.getDefault()).contains(search)){
                            historyBookingSearchList.add(it)
                        }
                    }

                    rvHistoryBooking?.adapter!!.notifyDataSetChanged()
                }else{
                    historyBookingSearchList.clear()
                    historyBookingSearchList.addAll(historyBookingList!!)
                    rvHistoryBooking?.adapter!!.notifyDataSetChanged()
                }
                return true
            }

        })
    }

    override fun onErrorHistoryBooking(msg: String?) {
        toast("Failed to get data history booking")?.show()
    }
}