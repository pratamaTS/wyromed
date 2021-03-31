package com.example.wyromed.Activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import com.chinodev.androidneomorphframelayout.NeomorphFrameLayout
import com.example.wyromed.Activity.Interface.BookingInterface
import com.example.wyromed.Activity.Presenter.BookingPresenter
import com.example.wyromed.Data.Model.BookingOrderDetails
import com.example.wyromed.Data.Model.BookingOrderHeader
import com.example.wyromed.Fragment.*
import com.example.wyromed.R
import com.example.wyromed.Response.Booking.DataBooking
import com.google.android.material.appbar.AppBarLayout
import com.shuhart.stepview.StepView
import kotlinx.android.synthetic.main.activity_booking.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.util.*
import kotlin.collections.ArrayList

class BookingActivity: BaseActivity(), BookingInterface {
    object TAGS{
        val TOKEN = "token"
        val TOKENTYPE = "token_type"
        val MESSAGE = "message"
    }
    var toolbar: Toolbar? = null
    var appbar: AppBarLayout? = null
    var fragmentManager: FragmentManager? = null
    val rentFragment: Booking2Fragment = Booking2Fragment()
    val prevFragment: Booking4Fragment = Booking4Fragment()
    val sumFragment: Booking5Fragment = Booking5Fragment()
    var listStep: ArrayList<String> = ArrayList()
    var stepView: StepView? = null
    var myBooking: ImageView? = null
    var backButton: ImageView? = null
    var message: String?  = null
    var step: Int = 1
    var bookOrderHeader: BookingOrderHeader = BookingOrderHeader()
    var bookOrderDetails: ArrayList<BookingOrderDetails> = ArrayList()
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button
    private lateinit var fragmentContainer: View
    private lateinit var layoutBack: NeomorphFrameLayout

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
        fragmentContainer = findViewById(R.id.fragment_container)
        btnNext = findViewById(R.id.btn_next_book)
        btnBack = findViewById(R.id.btn_back_book)
        layoutBack = findViewById(R.id.layout_btn_back_booking)

        if(message != null){
            toast(message.toString()).show()
        }

        listStep.add("Rental Item")
        listStep.add("Preview")
        listStep.add("Finish")

        stepView!!.setSteps(listStep)

        //Fragment
        fragmentContainer.visibility = View.VISIBLE
        fragmentManager = supportFragmentManager
        fragmentManager?.beginTransaction()?.replace(
            R.id.fragment_container,
            rentFragment
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

        btnBack.onClick {
            step = step - 1
            stepView?.go(step, true)
            fragmentManager?.popBackStack()
        }

        backButton?.onClick {
            finish()
        }

        btnNext.onClick {
            stepView?.go(step, true)
            when(step){
                1 -> {
                    layoutBack.visibility = View.VISIBLE
                    fragmentManager = supportFragmentManager
                    fragmentManager?.beginTransaction()?.replace(
                        R.id.fragment_container,
                        prevFragment
                    )?.commit()
                }
                2 -> {
                    layoutBack.visibility = View.GONE
                    btnNext.text = "Booking"
                    fragmentManager = supportFragmentManager
                    fragmentManager?.beginTransaction()?.replace(
                        R.id.fragment_container,
                        sumFragment
                    )?.commit()
                }
                3-> {
                    BookingPresenter(this@BookingActivity).booking(
                        this@BookingActivity,
                        bookOrderHeader,
                        bookOrderDetails
                    )
                }
            }
            step = step + 1
        }

    }

    fun addBooking(bookingOrderHeader: BookingOrderHeader, bookingOrderDetails: ArrayList<BookingOrderDetails>) {
        bundle.putParcelable("booking_order_header", bookingOrderHeader)
        bundle.putParcelableArrayList("booking_order_details", bookingOrderDetails)
        bookOrderHeader = bookingOrderHeader
        bookOrderDetails = bookingOrderDetails

        Log.d("Booking Order Header", bookingOrderHeader.toString())
        Log.d("Booking Order Details", bookingOrderDetails.toString())

        prevFragment.arguments = bundle
        sumFragment.arguments = bundle

    }

    override fun onSuccessBooking(message: String?, dataBooking: DataBooking?) {
        step = 1

        val openDialog = Dialog(this)
        openDialog.setContentView(R.layout.item_dialog_booking_success)
        val tvNoRequest = openDialog.findViewById<TextView>(R.id.tv_dialog_no_booking)
        val btnOk = openDialog.findViewById<Button>(R.id.dialog_btn_oke_booking)

        // Set Value
        tvNoRequest.text = dataBooking!!.boNumber

        // Open Dialog
        openDialog.setCanceledOnTouchOutside(true)

        btnOk.onClick {
            openDialog.dismiss()
            startActivity<OrderedActivity>(
                OrderedActivity.TAGS.MESSAGE to message
            )

            finish()
        }

        openDialog.show()
    }

    override fun onErrorBooking(msg: String?) {
        toast(msg.toString())
    }

}