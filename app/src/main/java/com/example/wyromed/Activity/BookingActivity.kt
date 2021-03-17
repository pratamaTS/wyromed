package com.example.wyromed.Activity

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.example.wyromed.Fragment.Booking1Fragment
import com.example.wyromed.Fragment.Booking2Fragment
import com.example.wyromed.Fragment.Booking3Fragment
import com.example.wyromed.R
import com.google.android.material.appbar.AppBarLayout
import com.shuhart.stepview.StepView
import kotlinx.android.synthetic.main.activity_booking.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*

class BookingActivity: BaseActivity() {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var toolbar: Toolbar? = null
    var appbar: AppBarLayout? = null
    var fragmentManager: FragmentManager? = null
    val formDetailFragment: Booking2Fragment = Booking2Fragment()
    var listStep: ArrayList<String> = ArrayList()
    var stepView: StepView? = null
    var myBooking: ImageView? = null
    var backButton: ImageView? = null
    var message: String?  = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        //INIT VIEW
        toolbar = findViewById(R.id.toolbar)
        appbar = findViewById(R.id.appbar)
        message = intent.getStringExtra("message")
        stepView = findViewById(R.id.step_view)
        myBooking = findViewById(R.id.ic_my_booking)
        backButton = findViewById(R.id.ic_back)

        if(message != null){
            toast(message.toString()).show()
        }

        listStep.add("Rental Item")
        listStep.add("Preview")
        listStep.add("Finish")

        stepView!!.setSteps(listStep)

        //Fragment
        fragmentManager = supportFragmentManager
        fragmentManager?.beginTransaction()?.replace(
            R.id.fragment_booking_container,
            formDetailFragment
        )?.commit()

        //Button
        initActionButton()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                val intent = Intent(this@BookingActivity, BookingActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun initActionButton() {
        myBooking?.onClick {
            val orderMessage = null
            startActivity<OrderedActivity>(
                OrderedActivity.TAGS.TOKENTYPE to user?.token_type,
                OrderedActivity.TAGS.TOKEN to user?.token,
                OrderedActivity.TAGS.MESSAGE to orderMessage)
        }

        backButton?.onClick {
            finish()
        }
    }

    fun nextStep() {
        stepView?.go(1, true);
    }

    fun nextStep2() {
        stepView?.go(2, true);
    }

}