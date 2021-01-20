package com.example.wyromed.Activity

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.wyromed.Activity.OnBoardingActivity.TAGS.MESSAGE
import com.example.wyromed.Adapter.ViewAnimAdapter
import com.example.wyromed.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_on_boarding.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.util.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class OnBoardingActivity : AppCompatActivity(){
    var viewAnim: ViewPager? = null
    var indicator: TabLayout? = null
    var btnSignup: Button? = null
    var btnSignIn:android.widget.Button? = null

    object TAGS{
        val MESSAGE = "message"
    }

    private val anim =
        arrayOf(R.raw.slider_1, R.raw.slider_2, R.raw.slider_3)
    var textAnim: List<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        //INIT VIEW
        viewAnim = findViewById<ViewPager>(R.id.view_anim)
        indicator = findViewById<TabLayout>(R.id.indicator)
        btnSignup = findViewById<Button>(R.id.btn_signup_to_layout)
        btnSignIn = findViewById<Button>(R.id.btn_signin_to_layout)

        textAnim = ArrayList<String>()
        (textAnim as ArrayList<String>).add("Medis")
        (textAnim as ArrayList<String>).add("Sewa Alat")
        (textAnim as ArrayList<String>).add("Pencatatan Operasi")

        viewAnim?.adapter = ViewAnimAdapter(this, anim, textAnim as ArrayList<String>)


        indicator?.setupWithViewPager(viewAnim, true)

        val timer = Timer()
        timer.scheduleAtFixedRate(
            SliderTimer(),
            4000,
            6000
        )

        if(MESSAGE != "message"){
            toast(MESSAGE).show()
        }


        initActionButton()
    }

    private inner class SliderTimer : TimerTask() {
        override fun run() {
            this@OnBoardingActivity.runOnUiThread(Runnable {
                if (viewAnim!!.getCurrentItem() < anim.size - 1) {
                    viewAnim!!.setCurrentItem(viewAnim!!.getCurrentItem() + 1)
                } else {
                    viewAnim?.setCurrentItem(0)
                }
            })
        }
    }

    private fun  initActionButton() {
        btn_signup_to_layout.onClick {
            startActivity<SignUpActivity>()
        }
        btn_signin_to_layout.onClick {
            startActivity<SignInActivity>()
        }
    }
}