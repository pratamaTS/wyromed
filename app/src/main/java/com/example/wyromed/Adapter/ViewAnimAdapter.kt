package com.example.wyromed.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.airbnb.lottie.LottieAnimationView
import com.example.wyromed.R


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ViewAnimAdapter(
    context: Context,
    anim: Array<Int>,
    text: List<String>
) :
    PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private val context: Context
    private var anim =
        arrayOf(R.raw.slider_1, R.raw.slider_2, R.raw.slider_3)
    private val text: List<String>
    override fun getCount(): Int {
        return anim.size
    }

    override fun isViewFromObject(
        view: View,
        `object`: Any
    ): Boolean {
        return view === `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.anim_slider, null)
        val lottieAnimationView =
            view.findViewById<View>(R.id.anim_lottie) as LottieAnimationView
        val textView =
            view.findViewById<View>(R.id.text_view_anim) as TextView
        lottieAnimationView.setAnimation(anim[position])
        textView.text = text[position]
        val vp = container as ViewPager
        vp.addView(view, 0)
        return view
    }

    override fun destroyItem(
        container: ViewGroup,
        position: Int,
        `object`: Any
    ) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

    init {
        this.context = context
        this.anim = anim
        this.text = text
    }
}