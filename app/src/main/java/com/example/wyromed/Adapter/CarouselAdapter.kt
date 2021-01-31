package com.example.wyromed.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import com.example.wyromed.Activity.BookingActivity
import com.example.wyromed.Activity.PatientActivity
import com.example.wyromed.Activity.SalesActivity
import com.example.wyromed.Activity.StockActivity
import com.example.wyromed.Model.Carousel
import com.example.wyromed.R
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarouselAdapter(
    models: ArrayList<Carousel>?,
    context: Context?,
) :
    PagerAdapter() {
    private val models: List<Carousel>
    var context: Context?


    override fun getCount(): Int {
        return models.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.card_item_fitur, container, false)
        val illustration: ImageView
        val date: TextView
        val title: TextView
        val total: TextView

        illustration = view.findViewById(R.id.iv_illustration_content)
        date = view.findViewById(R.id.tv_date_content)
        title = view.findViewById(R.id.tv_title_content)
        total = view.findViewById(R.id.tv_total_content)

        illustration.setImageResource(models[position].illustration)
        date.setText(models[position].date)
        title.setText(models[position].title)
        total.setText(models[position].total)

        val bookingPage = Intent(context, BookingActivity::class.java)
        val salesPage = Intent(context, SalesActivity::class.java)
        val stockPage = Intent(context, StockActivity::class.java)

        view.setOnClickListener {
            if (position == 0) {
                context?.startActivity(bookingPage)
            }
            if (position == 1) {
                context?.startActivity(salesPage)
            }
            if (position == 2) {
                context?.startActivity(stockPage)
            }
        }

        container.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    init {
        this.models = models!!
        this.context = context
    }
}