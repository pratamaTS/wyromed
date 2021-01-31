package com.example.wyromed.Fragment

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.wyromed.Activity.Interface.StockInterface
import com.example.wyromed.Activity.Interface.TotalBookingOrderInterface
import com.example.wyromed.Activity.Interface.TotalSalesOrderInterface
import com.example.wyromed.Activity.Interface.TotalStockInterface
import com.example.wyromed.Activity.Presenter.TotalBookingPresenter
import com.example.wyromed.Activity.Presenter.TotalSalesOrderPresenter
import com.example.wyromed.Activity.Presenter.TotalStockItemPresenter
import com.example.wyromed.Adapter.CarouselAdapter
import com.example.wyromed.Model.Carousel
import com.example.wyromed.R
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment(), TotalBookingOrderInterface, TotalSalesOrderInterface, TotalStockInterface {
    var viewPager: ViewPager? = null
    var models: ArrayList<Carousel> = ArrayList()
    var adapter: CarouselAdapter? = null
    var totalBookingOrder: String = "0"
    var totalSslesOrder: String = "0"
    var totalStock: String = "0"
    var formatted: String? = null
    var count: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        view.context
        //INIT VIEW
        viewPager = view.findViewById(R.id.view_pager_carousel)

        val current = LocalDateTime.now()

        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        formatted = current.format(formatter)

        if(count == 0) {
            models?.add(
                0,
                Carousel(
                    R.drawable.illustration_booking,
                    formatted.toString(),
                    "Booking",
                    totalBookingOrder.toString()
                )
            )
            models?.add(
                1,
                Carousel(
                    R.drawable.illustration_sales,
                    formatted.toString(),
                    "Sales",
                    totalSslesOrder.toString()
                )
            )
            models?.add(
                2,
                Carousel(
                    R.drawable.illustration_stock,
                    formatted.toString(),
                    "Stock",
                    totalStock.toString()
                )
            )
        }
        getTotal()

        return view
    }

    fun getTotal(){
        TotalBookingPresenter(this).getTotalBookingOrder(requireContext())
        TotalSalesOrderPresenter(this).getTotalSalesOrder(requireContext())
        TotalStockItemPresenter(this).getTotalStockItem(requireContext())
    }

    override fun onSuccessGetTotalBookingOrder(dataTotalBookingOrder: Int?) {
        totalBookingOrder = dataTotalBookingOrder.toString()

        count = 1

        models?.set(0, Carousel(R.drawable.illustration_booking, formatted.toString(), "Booking", totalBookingOrder.toString()))

        bindAdapter()
    }

    override fun onErrorGetTotalBookingOrder(msg: String?) {
        toast(msg ?: "Failed to get total booking order").show()
    }

    override fun onSuccessGetTotalSalesOrder(dataTotalSalesOrder: Int?) {
        totalSslesOrder = dataTotalSalesOrder.toString()

        models?.set(1, Carousel(R.drawable.illustration_sales, formatted.toString(), "Sales", totalSslesOrder.toString()))

        bindAdapter()
    }

    override fun onErrorGetTotalSalesOrder(msg: String?) {
        toast(msg ?: "Failed to get total sales order").show()
    }

    override fun onSuccessGetTotalStockItem(dataTotalStockItem: Int?) {
        totalStock = dataTotalStockItem.toString()

        models?.set(2, Carousel(R.drawable.illustration_stock, formatted.toString(), "Stock", totalStock.toString()))

        bindAdapter()
    }

    override fun onErrorGetTotalStockItem(msg: String?) {
        toast(msg ?: "Failed to get total stock item").show()
    }

    private fun bindAdapter(){
        adapter = CarouselAdapter(models, activity as Context?)

        viewPager!!.adapter = adapter
        viewPager!!.setClipToPadding(false)
        viewPager!!.setClipChildren(false)
        viewPager!!.setOffscreenPageLimit(3)
    }
}